package plugins;

import interfaces.ITamagotchi;

public class Tamagotchi implements ITamagotchi {

	private boolean content = true;
	private int faim = 40;
	private int propre = 70;
	private int ennui = 0;
	private String nom = "Test";
	private boolean sexe = true;
	private int espece = 1;
	
	/* (non-Javadoc)
	 * @see plugins.ITamagochi#isContent()
	 */
	@Override
	public boolean isContent() {
		return content;
	}
	/* (non-Javadoc)
	 * @see plugins.ITamagochi#setContent(boolean)
	 */
	@Override
	public void setContent(boolean content) {
		this.content = content;
	}
	/* (non-Javadoc)
	 * @see plugins.ITamagochi#getFaim()
	 */
	@Override
	public int getFaim() {
		return faim;
	}
	/* (non-Javadoc)
	 * @see plugins.ITamagochi#setFaim(int)
	 */
	@Override
	public void setFaim(int faim) {
		this.faim = faim;
	}
	/* (non-Javadoc)
	 * @see plugins.ITamagochi#getPropre()
	 */
	@Override
	public int getPropre() {
		return propre;
	}
	/* (non-Javadoc)
	 * @see plugins.ITamagochi#setPropre(int)
	 */
	@Override
	public void setPropre(int propre) {
		this.propre = propre;
	}
	/* (non-Javadoc)
	 * @see plugins.ITamagochi#getEnnui()
	 */
	@Override
	public int getEnnui() {
		return ennui;
	}
	/* (non-Javadoc)
	 * @see plugins.ITamagochi#setEnnui(int)
	 */
	@Override
	public void setEnnui(int ennui) {
		this.ennui = ennui;
	}
	/* (non-Javadoc)
	 * @see plugins.ITamagochi#getNom()
	 */
	@Override
	public String getNom() {
		return nom;
	}
	/* (non-Javadoc)
	 * @see plugins.ITamagochi#setNom(java.lang.String)
	 */
	@Override
	public void setNom(String nom) {
		this.nom = nom;
	}
	/* (non-Javadoc)
	 * @see plugins.ITamagochi#isSexe()
	 */
	@Override
	public boolean isSexe() {
		return sexe;
	}
	/* (non-Javadoc)
	 * @see plugins.ITamagochi#setSexe(boolean)
	 */
	@Override
	public void setSexe(boolean sexe) {
		this.sexe = sexe;
	}
	/* (non-Javadoc)
	 * @see plugins.ITamagochi#getEspece()
	 */
	@Override
	public int getEspece() {
		return espece;
	}
	/* (non-Javadoc)
	 * @see plugins.ITamagochi#setEspece(int)
	 */
	@Override
	public void setEspece(int espece) {
		this.espece = espece;
	}
	
	
}
