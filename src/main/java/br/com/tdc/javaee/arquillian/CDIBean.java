package br.com.tdc.javaee.arquillian;
import javax.enterprise.context.RequestScoped;

@RequestScoped 
public class CDIBean {
	
	public boolean doSomethingThatFail(){
		boolean a=false;
		if(a){
			a = !a;
		}
		return a;
	}
	
}
