package br.com.tdc.javaee.arquillian.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
@Entity public class Game implements java.io.Serializable {

    public Game(String name){
        this.setName(name);
    }

    public Game() {
    }

    @Id 
    private @GeneratedValue(strategy=GenerationType.AUTO) @Column(name="id",updatable=false,nullable=false) Long id=null;

    @Version private @Column(name="version") int version=0;
    public Long getId(){
        return this.id;
    }
    public void setId(  final Long id){
        this.id=id;
    }
    public int getVersion(){
        return this.version;
    }
    public void setVersion(  final int version){
        this.version=version;
    }
    @Column private String name;
    public String getName(){
        return this.name;
    }
    public void setName(  final String name){
        this.name=name;
    }
    @Column private String publisher;
    public String getPublisher(){
        return this.publisher;
    }
    public void setPublisher(  final String publisher){
        this.publisher=publisher;
    }
}

