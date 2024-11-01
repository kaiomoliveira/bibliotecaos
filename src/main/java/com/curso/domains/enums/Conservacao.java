package com.curso.domains.enums;


public enum Conservacao {
    EXELENTE(0,"EXELENTE"),BOM(1,"BOM"),MARCASDEUSO(2,"MARCAS DE USO"),GASTO(3,"GASTO");

    private Integer id;
    private String situacao;

    Conservacao(Integer id, String situacao) {
        this.id = id;
        this.situacao = situacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public static Conservacao toEnum(Integer id){
        if(id==null) return null;
        for(Conservacao x : Conservacao.values()){
            if(id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("Conservação inválida");
    }
}