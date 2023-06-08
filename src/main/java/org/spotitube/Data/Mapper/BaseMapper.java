package org.spotitube.Data.Mapper;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseMapper<T> implements IBaseMapper<T> {

    // Initialize Connection
    protected Connection getConnection() {
        Properties properties = new Properties();
        Connection connection;
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
            Class.forName(properties.getProperty("driver.class.name"));

            SQLServerDataSource dataSource = new SQLServerDataSource();
            dataSource.setUser(properties.getProperty("db.username"));
            dataSource.setPassword(properties.getProperty("db.password"));
            dataSource.setServerName(properties.getProperty("db.serverName"));
            dataSource.setPortNumber(Integer.parseInt(properties.getProperty("db.portNumber")));
            dataSource.setDatabaseName(properties.getProperty("db.databaseName"));
            dataSource.setTrustServerCertificate(true);

            connection = dataSource.getConnection();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    @Override
    public Optional<T> find(int id) {
        return Optional.empty();
    }

    @Override
    public void save(String query, List<Object> queryParams) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set query parameters
            for (int i = 0; i < queryParams.size(); i++) {
                stmt.setObject(i + 1, queryParams.get(i));
            }

            // Execute the query
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> all(String query, List<Object> queryParams) {
        List<T> results = new ArrayList<T>();

        try(Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)){

            // Check if queryParams parameter is set
            if(queryParams.size() >= 1){
                // Set query parameters
                for(int i = 0; i < queryParams.size(); i++) {
                    Object param = queryParams.get(i);
                    int parameterIndex = i + 1;

                    if (param != null) {
                        switch (param.getClass().getSimpleName()) {
                            case "Integer":
                                stmt.setInt(parameterIndex, (Integer) param);
                                break;
                            case "String":
                                stmt.setString(parameterIndex, (String) param);
                                break;
                            case "Boolean":
                                stmt.setBoolean(parameterIndex, (Boolean) param);
                                break;
                            case "Double":
                                stmt.setDouble(parameterIndex, (Double) param);
                                break;
                            // Add cases for other primitive data types as needed
                            default:
                                // Handle unsupported data type
                                throw new IllegalArgumentException("Unsupported data type: " + param.getClass().getSimpleName());
                        }
                    } else {
                        stmt.setNull(parameterIndex, java.sql.Types.NULL);
                    }
                }
            }


            // Execute the query
            ResultSet resultSet = stmt.executeQuery();

            // Process the results
            while(resultSet.next()) {
                T result = createInstance();

                // Retrieve and set properties using reflection
                Field[] fields = result.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);  // Allow access to private fields

                    String columnName = getColumnName(field);  // Get the corresponding column name
                    Object columnValue = resultSet.getObject(columnName);

                    // Set the value into the field
                    if (columnValue instanceof Date) {
                        field.set(result, ((Date) columnValue).toLocalDate());
                    } else if (columnValue instanceof Short) {
                        field.set(result, (Short)columnValue != 0);
                    }else{
                        field.set(result, columnValue);
                    }
                }

                // Retrieve declared fields of the superclass
                Class<?> superClass = result.getClass().getSuperclass();
                if (superClass != null) {
                    Field[] superDeclaredFields = superClass.getDeclaredFields();
                    for (Field field : superDeclaredFields) {
                        field.setAccessible(true);  // Allow access to private fields

                        String columnName = getColumnName(field);  // Get the corresponding column name
                        Object columnValue = resultSet.getObject(columnName);

                        // Set the value into the field
                        if (columnValue instanceof Date) {
                            field.set(result, ((Date) columnValue).toLocalDate());
                        } else if (columnValue instanceof Short) {
                            field.set(result, (Short)columnValue != 0);
                        }else{
                            field.set(result, columnValue);
                        }


                    }
                }

                results.add(result);
            }

        } catch(SQLException | ReflectiveOperationException e) {
            e.printStackTrace();
        }

        return results;
    }

    private T createInstance() throws ReflectiveOperationException {
        return (T) Class.forName(getClassName()).getDeclaredConstructor().newInstance();
    }

    private String getClassName() {
        return ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
    }

    private String getColumnName(Field field) {
        // Use JavaBeans conventions to derive the column name from the field name
        String fieldName = field.getName();
        return Character.toLowerCase(fieldName.charAt(0)) + fieldName.substring(1);
    }
}
