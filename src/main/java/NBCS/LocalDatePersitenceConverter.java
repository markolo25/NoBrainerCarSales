/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBCS;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * JPA converter for support of LocalDate
 * @author Xavier Martinez <xavier.martinez@student.csulb.edu>
 */
@Converter(autoApply = true)
public class LocalDatePersitenceConverter implements AttributeConverter<LocalDate, Date> {
    // Based on example from https://www.thoughts-on-java.org/persist-localdate-localdatetime-jpa/

    @Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
    	return (locDate == null ? null : Date.valueOf(locDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
    	return (sqlDate == null ? null : sqlDate.toLocalDate());
    }
}
