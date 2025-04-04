package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

public enum TipoTrabajo {
    MECANICO("Mécanico"),
    REVISION("Revisión");

    private String nombre;

    private TipoTrabajo(String nombre){
        this.nombre = nombre;
    }

    public static TipoTrabajo get(Trabajo trabajo){
        TipoTrabajo tipoTrabajo = null;
        if (trabajo instanceof Mecanico){
            tipoTrabajo = MECANICO;
        }else if(trabajo instanceof Revision){
            tipoTrabajo = REVISION;
        }
        return tipoTrabajo;
    }

    @Override
    public String toString() {
        return String.format("%s", nombre);
    }
}
