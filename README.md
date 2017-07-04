# Iona

Iona is a software made for doctors, owners of small clinics, that want to register their patients appointments.

## Configuration

### Database

Iona was built using MySQL 5.x. To properly use it,  add a datasource configuration to wildfly between the following tags:

```xml
<datasource jta="true" jndi-name="java:jboss/datasources/ionaDS" pool-name="ionaDS" enabled="true">
</datasource>
```

### Security

Iona needs a JAAS Configuration in Wildfly to enable access to protected pages. First, you need to add the following **security-domain** to your configuration file:

```xml
<security-domain name="jaas-realm">
    <authentication>
        <login-module code="Database" flag="required">
            <module-option name="dsJndiName" value="java:jboss/datasources/ionaDS"/>
            <module-option name="principalsQuery" value="select password from users where username=?"/>
            <module-option name="rolesQuery" value="select name, 'Roles' from users_roles r join users u where u.username=?"/>
            <module-option name="hashAlgorithm" value="SHA-256"/>
            <module-option name="hashEncoding" value="base64"/>
        </login-module>
    </authentication>
</security-domain>
```

After that, make sure you have started the application and you already have all the tables created in your database (update value on development environment), then run the following script to prepare the application for logins:

```sql
insert into users(username, password) values ('admin', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=');

insert into users_roles(id_user, name) values (1, 'ADMIN');
```

Start the aplication and you will be able to login with the username **admin** and password **admin**.
