package org.example.log_rest.model;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.log_rest.enums.LogLevel;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "log_data")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
        // PostgreSQLEnumType - обрабатывает преобразование между перечислениями Java и типами перечислений PostgreSQL
)
public class LogData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    @Type(type = "pgsql_enum")  // это пользовательский тип
    private LogLevel level;

    @Column(name = "src", nullable = false)
    private String src;

    @Column(name = "message", nullable = false)
    private String message;
}
