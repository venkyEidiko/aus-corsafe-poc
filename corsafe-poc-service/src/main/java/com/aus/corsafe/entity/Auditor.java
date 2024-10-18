package com.aus.corsafe.entity;


import com.aus.corsafe.converter.ListConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Auditor_Details")
public class Auditor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    int currentTaskCount;
    String location;

    @Convert(converter = ListConverter.class)
    List<String> nearByArea;
}
