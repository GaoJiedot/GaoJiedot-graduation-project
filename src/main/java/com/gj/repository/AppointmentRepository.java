package com.gj.repository;

import com.gj.pojo.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

    Appointment findAppointmentByShopId(Integer shopId);

    @Query("SELECT a FROM Appointment a WHERE a.shopId = :shopId AND a.appointmentDate = :date")
    List<Appointment> getReservedTime(@Param("shopId") Integer shopId, @Param("date") String date);
}