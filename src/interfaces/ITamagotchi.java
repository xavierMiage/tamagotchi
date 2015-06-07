package interfaces;

public interface ITamagotchi {

	public abstract boolean isContent();

	public abstract void setContent(boolean content);

	public abstract int getFaim();

	public abstract void setFaim(int faim);

	public abstract int getPropre();

	public abstract void setPropre(int propre);

	public abstract int getEnnui();

	public abstract void setEnnui(int ennui);

	public abstract String getNom();

	public abstract void setNom(String nom);

	public abstract boolean isSexe();

	public abstract void setSexe(boolean sexe);

	public abstract int getEspece();

	public abstract void setEspece(int espece);

}