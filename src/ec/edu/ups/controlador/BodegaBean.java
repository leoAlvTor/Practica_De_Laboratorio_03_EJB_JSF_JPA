package ec.edu.ups.controlador;


import ec.edu.ups.ejb.BodegaFacade;
import ec.edu.ups.entidad.*;
import ec.edu.ups.redes.HttpClient;
import ec.edu.ups.redes.OnHttpRequestComplete;
import ec.edu.ups.redes.Response;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class BodegaBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @EJB
    private BodegaFacade ejbBodegaFacade;
    private List<Bodega> bodegas;
    private String nombre;
    /*ADICION*/
    private String level1, level2, level3;
    private boolean level2ListDisabled = true, level3ListDisabled = true;
    private Map<String, String> paises;
    private String codePais;
    private PaisBean paisBean;
    private ProvinciaBean provinciaBean;
    private CiudadBean ciudadBean;

    public BodegaBean(){

    }


    @PostConstruct
    public void init(){
        bodegas=ejbBodegaFacade.findAll();

    }

    public Bodega[] getBodegas() {
        return bodegas.toArray(new Bodega[0]);
    }

    public void setBodegas(List<Bodega> bodegas) {
        this.bodegas = bodegas;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }


    public String add (){

//        ejbBodegaFacade.create(new Bodega(this.nombre));
        System.out.println(this.level3);

        String [] paisProCiu=this.level3.split("-");
        paisBean=new PaisBean();
        provinciaBean=new ProvinciaBean();
        ciudadBean=new CiudadBean();

        Pais p=paisBean.consultarPais(paisProCiu[0].toUpperCase());
        Provincia pro=provinciaBean.consultarProvincia(paisProCiu[1].toUpperCase(),p);
        Ciudad c=ciudadBean.consultarCiudad(paisProCiu[3].toUpperCase(),pro);

        return  null;
    }

    public String deleted(Bodega b){
        ejbBodegaFacade.remove(b);
        bodegas=ejbBodegaFacade.findAll();
        return null;
    }

    public String edit (Bodega b){
        b.setEditable(true);
        return null;
    }

    public String save(Bodega b){
        ejbBodegaFacade.edit(b);
        b.setEditable(false);
        return null;
    }
    /*ADICION*/
    public boolean isLevel2ListDisabled() {
        return level2ListDisabled;
    }

    public void setLevel2ListDisabled(boolean level2ListDisabled) {
        this.level2ListDisabled = level2ListDisabled;
    }

    public boolean isLevel3ListDisabled() {
        return level3ListDisabled;
    }

    public void setLevel3ListDisabled(boolean level3ListDisabled) {
        this.level3ListDisabled = level3ListDisabled;
    }

    public String getLevel1() {
        return level1;
    }

    public String getLevel2() {
        return level2;
    }

    public String getLevel3() {
        return level3;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
        this.setLevel2("---Elige---");
        this.level2ListDisabled = this.level1.equals("---Elige---");
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
        this.setLevel3("---Elige---");
        this.level3ListDisabled = this.level2.equals("---Elige---");
    }

    public void setLevel3(String level3) {
        this.level3 = level3;
    }

    private ArrayList<String> generateList(String pre, int size) {
        //String[] list = new String[size];
        ArrayList<String> list=new ArrayList<String>();
        list.add("---Elige---");

        return list;
    }

    private ArrayList<String> obtenerPaises() {
        ArrayList<String> paisesf=new ArrayList<String>();
        paisesf.add("---Elige---");

        HttpClient cliente = new HttpClient(new OnHttpRequestComplete() {
            @Override
            public void onComplete(Response status) {
                if (status.isSuccess()) {

                    String pedro = status.getResult();

                    pedro = pedro.substring(0, pedro.length() - 1);
                    String[] p = pedro.split("}");

                    /* Paises */
                    paises=new HashMap<>();
                    for (String pp : p) {
                        pp = pp.substring(9, pp.length());

                        String[] pp2 = pp.split(",");
                        String key = pp2[0].substring(1, pp2[0].length() - 1);
                        String value = pp2[1].substring(pp2[1].length() - 3, pp2[1].length() - 1);

                        paises.put(key, value);
                        paisesf.add(key);
                    }


                }

            }
        });

        cliente.excecute("http://battuta.medunes.net/api/country/all/?key=14aa95483fee2c14f4e20d45a0bac62e");
        return paisesf;
    }


    private ArrayList<String> obtenerProvincias(String pais){

        ArrayList<String> provincias=new ArrayList<String>();
        provincias.add("---Elige---");
        HttpClient cliente = new HttpClient(new OnHttpRequestComplete() {
            @Override
            public void onComplete(Response status) {
                if (status.isSuccess()) {

                    String pedro = status.getResult();

                    pedro = pedro.substring(0, pedro.length() - 1);
                    String[] p = pedro.split("}");

                    for (String pp : p) {
                        pp=pp.substring(11, pp.length());

                        String [] pp2=pp.split(",");
                        String key=pp2[0].substring(1, pp2[0].length()-1);
                        provincias.add(pais+"-"+key);

                    }


                }

            }
        });
        this.codePais=paises.get(pais);
        cliente.excecute("http://battuta.medunes.net/api/region/"+this.codePais+"/all/?key=14aa95483fee2c14f4e20d45a0bac62e");
        return provincias;
    }

    private ArrayList<String> obtenerCiudades(String provincia){

        ArrayList<String> ciudades=new ArrayList<String>();
        ciudades.add("---Elige---");
        HttpClient cliente = new HttpClient(new OnHttpRequestComplete() {
            @Override
            public void onComplete(Response status) {
                if (status.isSuccess()) {

                    String pedro = status.getResult();

                    pedro = pedro.substring(0, pedro.length() - 1);
                    String[] p = pedro.split("}");

                    for (String pp : p) {
                        pp=pp.substring(9, pp.length());

                        String [] pp2=pp.split(",");
                        String key=pp2[0].substring(1, pp2[0].length()-1);
                        ciudades.add(provincia+"-"+key);
                    }


                }

            }
        });
        String[] provinciaF=provincia.split(" ");

        cliente.excecute("http://battuta.medunes.net/api/city/"+this.codePais+"/search/?region="+provinciaF[provinciaF.length-1]+"&key=14aa95483fee2c14f4e20d45a0bac62e");
        return ciudades;
    }


    public ArrayList<String> getLevel1List() {
        //this.generateList("", 5);


        //return this.generateList("", 5);
        return obtenerPaises();
    }

    public ArrayList<String> getLevel2List() {
        if (this.level2ListDisabled)
            return this.generateList("", 1);
        else//return this.generateList(this.level1 + "--", 5);
            return this.obtenerProvincias(this.level1);
    }

    public ArrayList<String> getLevel3List() {
        if (this.level3ListDisabled)
            return this.generateList("", 1);
        else//return this.generateList(this.level2 + "--", 5);
            return this.obtenerCiudades(this.level2);
    }




}
