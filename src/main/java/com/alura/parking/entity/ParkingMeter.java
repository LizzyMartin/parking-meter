package com.alura.parking.entity;

import com.alura.parking.enums.PaymentTimeType;
import com.alura.parking.enums.PaymentType;
import com.alura.parking.enums.Status;
import com.alura.parking.exceptions.InvalidPaymentException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ParkingMeter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final LocalDateTime startTime = LocalDateTime.now();

    private LocalDateTime finishTime;

    private PaymentTimeType paymentTimeType;

    @OneToOne
    private Vehicle vehicle;

    @OneToOne
    private Conductor conductor;

    private Status status = Status.ACTIVE;

    private BigDecimal totalToPay;

    private Duration timeParked;

    private Long hours;

    private boolean paid = false;

    public ParkingMeter(PaymentTimeType paymentTimeType, Vehicle vehicle, Conductor conductor) {
        this.paymentTimeType = paymentTimeType;
        this.vehicle = vehicle;
        this.conductor = conductor;
    }

    public ParkingMeter(PaymentTimeType paymentTimeType, Vehicle vehicle, Conductor conductor, Long hours) {
        this.paymentTimeType = paymentTimeType;
        this.vehicle = vehicle;
        this.conductor = conductor;
        this.hours = hours;
    }

    public void finish() {
        this.status = Status.FINISHED;
        this.finishTime = LocalDateTime.now();

        this.timeParked = Duration.between(this.startTime, this.finishTime);
        this.totalToPay = calculateTotalToPay(this.timeParked);
    }

    private BigDecimal calculateTotalToPay(Duration timeParked) {
        if (PaymentTimeType.FIXED.equals(this.paymentTimeType)) {
            return this.paymentTimeType.getPrice();
        } else {
            long timeParkedHours = timeParked.toHours();
            if (timeParkedHours > 0) {
                return this.paymentTimeType.getPrice().multiply(BigDecimal.valueOf(hours)).setScale(2, BigDecimal.ROUND_HALF_UP);
            } else {
                return this.paymentTimeType.getPrice();
            }
        }
    }

    public void pay(PaymentType paymentType) throws InvalidPaymentException {
        if (isPaymentTypeAvailable(paymentType)) {
            this.paid = true;
        } else {
            throw new InvalidPaymentException();
        }
    }

    @Scheduled(fixedDelayString = "${this.getHoursInMs()}")
    private void notifyFixed() {
        if (PaymentTimeType.FIXED.equals(this.paymentTimeType))
            log.info("Your parking time is close to expire!");
    }

    @Scheduled(cron = "0 * * * *")
    private void notifyVariable() {
        if (PaymentTimeType.PER_HOUR.equals(this.paymentTimeType) && !this.paid)
            log.info("Your parking time will be extended!");
    }

    private String getHoursInMs() {
        long hoursInMs = this.hours * 3600000;
        return String.valueOf(hoursInMs);
    }

    private boolean isPaymentTypeAvailable(PaymentType paymentType) {
        return this.paymentTimeType.getAvailablePaymentTypes().contains(paymentType);
    }

}
