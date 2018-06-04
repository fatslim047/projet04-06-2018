import java.io.IOException;

public class OpinionWord {
public String Aspect ; 	
public String Oword ;
public boolean  negation ;
public double value ; 
public String pos ;


	public OpinionWord( String Aspect , String Oword, String pos, boolean negation) throws IOException {
	SentiWordNetDemoCode senti = new SentiWordNetDemoCode("C:/Users/Helmi/eclipse-workspace/stanford/sentiwordnet.txt");
	this.Oword = Oword;
	this.negation = negation;
	this.Aspect= Aspect; 
	this.pos = pos;
	if (negation == false) {
		value = senti.extract(Oword, pos);
	}
	else
		value=-(senti.extract(Oword,pos));
	if (pos=="v" && value==0 )
	{
		pos="a";
	this.pos="a";
		if (negation==false)
			value=senti.extract(Oword,pos);
		else
		value = senti.extract(Oword,pos);
	}
}

	public String getAspect() {
		return Aspect;
	}

	@Override
public String toString() {
	return "Opinionword [ Aspect "+Aspect+" word=" + Oword + ", negation=" + negation + ",   pos=" + pos +" its value"+value+"]";
}

	public void setAspect(String aspect) {
		Aspect = aspect;
	}
}

