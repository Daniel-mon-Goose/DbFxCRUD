module ru.nsu.protasov {
    requires java.persistence;
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.context;
    requires spring.core;
    requires java.sql;
    requires java.annotation;
    requires spring.data.jpa;
    requires spring.jdbc;
    requires spring.orm;
    requires spring.tx;
    requires org.hibernate.orm.core;
    requires spring.webmvc;
    requires java.xml.bind;
    requires spring.beans;
    requires net.bytebuddy;
    requires org.postgresql.jdbc;
    requires spring.data.commons;
    requires spring.boot.autoconfigure;
    requires com.fasterxml.jackson.databind;
    requires spring.context.support;

    opens ru.nsu.protasov to javafx.fxml, jakarta.activation;
    opens ru.nsu.protasov.config to spring.core, jakarta.activation;
    opens ru.nsu.protasov.entity to spring.core, org.hibernate.orm.core, spring.beans;
    opens ru.nsu.protasov.service to spring.beans, spring.core;
    opens ru.nsu.protasov.repository to spring.beans, spring.core;
    exports ru.nsu.protasov;
    exports ru.nsu.protasov.config;
    exports ru.nsu.protasov.entity;
}