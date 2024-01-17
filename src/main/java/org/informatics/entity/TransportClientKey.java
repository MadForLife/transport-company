package org.informatics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TransportClientKey implements Serializable {

    @Column(name = "transport_transport_id")
    private Long transportId;

    @Column(name = "client_client_id")
    private Long clientId;

    public Long getTransportId() {
        return transportId;
    }

    public void setTransportId(Long transportId) {
        this.transportId = transportId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public TransportClientKey() {  }

    public TransportClientKey(Long transportId, Long clientId) {
        this.transportId = transportId;
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransportClientKey that = (TransportClientKey) o;
        return Objects.equals(transportId, that.transportId) && Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transportId, clientId);
    }

    @Override
    public String toString() {
        return "TransportClientKey{" +
                "transportId=" + transportId +
                ", clientId=" + clientId +
                '}';
    }
}
