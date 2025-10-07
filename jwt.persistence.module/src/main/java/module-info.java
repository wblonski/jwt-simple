module jwt.persistence.module {
    requires jakarta.persistence;
    requires spring.data.jpa;
    requires spring.data.commons;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires spring.tx;
    requires spring.security.core;
    requires org.hibernate.orm.core;
    
    // Export repository packages so other modules can access them
    exports pl.wblo.repository;
    exports pl.wblo.repository.entity;
    
    // Open entity package for JPA/Hibernate
    opens pl.wblo.repository.entity to
            org.hibernate.orm.core,
            spring.core,
            spring.beans;
    
    opens pl.wblo.repository to spring.core, spring.beans, spring.data.commons;
}
