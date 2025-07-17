package com.cadastro.sistema.dto;

import com.cadastro.sistema.entities.Client;

public class ClientsDTO {

    private int id;
    private String name;
    private String cpf;
    private Double income;
    private String birthDate; // Para facilitar a serialização JSON
    private int children;


    public ClientsDTO() {
    }

    public ClientsDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.income = client.getIncome();
        this.birthDate = client.getBirthDate().toString();
        this.children = client.getChildren();
    }

    public ClientsDTO(int id, String name, String cpf, Double income, String birthDate, int children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public int getChildren() {
        return children;
    }
}
