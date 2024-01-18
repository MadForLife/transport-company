package org.informatics.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transport_has_client")
public class TransportClient {

    @EmbeddedId
    private TransportClientKey transportClientKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("transportId")
    @JoinColumn(name = "transport_transport_id")
    private Transport transport;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("clientId")
    @JoinColumn(name = "client_client_id")
    private Client client;

    @Column(name = "client_has_paid", nullable = false)
    private Boolean clientHasPaid;

    public TransportClientKey getTransportClientKey() {
        return transportClientKey;
    }

    public void setTransportClientKey(TransportClientKey transportClientKey) {
        this.transportClientKey = transportClientKey;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Boolean getClientHasPaid() {
        return clientHasPaid;
    }

    public void setClientHasPaid(Boolean clientHasPaid) {
        this.clientHasPaid = clientHasPaid;
    }

    public  TransportClient() {  }

    public TransportClient(TransportClientKey transportClientKey, Transport transport, Client client, Boolean clientHasPaid) {
        this.transportClientKey = transportClientKey;
        this.transport = transport;
        this.client = client;
        this.clientHasPaid = clientHasPaid;
    }

    @Override
    public String toString() {
        return "TransportClient{" +
                "transportClientKey=" + transportClientKey +
                ", transport=" + transport +
                ", client=" + client +
                ", clientHasPaid=" + clientHasPaid +
                '}';
    }
}
