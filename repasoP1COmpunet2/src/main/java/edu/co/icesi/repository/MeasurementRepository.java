package edu.co.icesi.repository;

import edu.co.icesi.model.Measurement;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MeasurementRepository {
    private final List<Measurement> measurements = new ArrayList<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public Measurement save(Measurement measurement) {
        if (measurement.getId() == null) {
            measurement.setId(idGenerator.getAndIncrement());
        }
        measurements.removeIf(m -> m.getId().equals(measurement.getId()));
        measurements.add(measurement);
        return measurement;
    }

    public List<Measurement> findByDeviceId(Integer assetId) {
        return measurements.stream()
                .filter(m -> m.getAssetId().equals(assetId))
                .toList();
    }

    public boolean existsByDeviceId(Integer assetId) {
        return measurements.stream().anyMatch(m -> m.getAssetId().equals(assetId));
    }

    public List<Measurement> findAll() {
        return new ArrayList<>(measurements);
    }

    @PostConstruct
    public void init() {
        System.out.println("Inicializando MeasurementRepository con datos de ejemplo...");

        Measurement m1 = new Measurement();
        m1.setFechaHora("2025-02-23 10:30");
        m1.setValor(25.5);
        m1.setUnidad("°C");
        m1.setAssetId(1);
        save(m1);

        Measurement m2 = new Measurement();
        m2.setFechaHora("2025-02-23 11:00");
        m2.setValor(1.2);
        m2.setUnidad("bar");
        m2.setAssetId(2);
        save(m2);

        System.out.println("Mediciones inicializadas: " + measurements.size());
    }
}