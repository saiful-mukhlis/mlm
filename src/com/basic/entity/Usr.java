package com.basic.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.basic.db.FUsr;
import com.global.util.Account;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class Usr {
	private String code;
	private String username;
	private String password;
	private Grp grp;
	private String nama;
	private String alamat;
	private String kota;
	private String noIdentitas;
	private String jenisIdentitas;
	private String kotaLahir;
	private Date tglLahir;
	private int jenisKelamin;
	private String noTelp;
	private String noHp1;
	private String noHp2;
	private String pinBb;
	private Date tglMasuk;
	private BigDecimal gaji;
	private JenisPekerjaan jenisPekerjaan;
	private String pendidikanTerakhir;
	private int status;
	
	private String createBy;
	private String updateBy;
	private Date createAt;
	private Date updateAt;

	private ODocument doc;
	
	

	public Usr(ODocument doc) {
		super();
		setDoc(doc);
	}

	public Usr() {
		doc=new ODocument(FUsr.TABLE);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		doc.field(FUsr.CODE, code, OType.STRING);
		this.code = code;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		doc.field(FUsr.USERNAME, username, OType.STRING);
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		try {
			doc.field(FUsr.PASSWORD, Account.md5(password), OType.STRING);
			this.password = password;
		} catch (Exception e) {
		}
	}
	
	public void setPasswordTanpaMd5(String password) {
		doc.field(FUsr.PASSWORD, password, OType.STRING);
		this.password = password;
	}

	public Grp getGrp() {
		return grp;
	}

	public void setGrp(Grp grp) {
		doc.field(FUsr.GRP, grp.getDoc(), OType.LINK);
		this.grp = grp;
	}
	public void setGrp(ODocument grp) {
		doc.field(FUsr.GRP, grp, OType.LINK);
		this.grp = new Grp(grp);
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		doc.field(FUsr.NAMA, nama, OType.STRING);
		this.nama = nama;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {

		doc.field(FUsr.ALAMAT, alamat, OType.STRING);
		this.alamat = alamat;
	}

	public String getKota() {
		return kota;
	}

	public void setKota(String kota) {
		doc.field(FUsr.KOTA, kota, OType.STRING);
		this.kota = kota;
	}

	public String getNoIdentitas() {
		return noIdentitas;
	}

	public void setNoIdentitas(String noIdentitas) {
		doc.field(FUsr.NO_IDENTITAS, noIdentitas, OType.STRING);
		this.noIdentitas = noIdentitas;
	}

	public String getJenisIdentitas() {
		return jenisIdentitas;
	}

	public void setJenisIdentitas(String jenisIdentitas) {
		doc.field(FUsr.JENIS_IDENTITAS, jenisIdentitas, OType.STRING);
		this.jenisIdentitas = jenisIdentitas;
	}

	public String getKotaLahir() {
		return kotaLahir;
	}

	public void setKotaLahir(String kotaLahir) {
		doc.field(FUsr.KOTA_LAHIR, kotaLahir, OType.STRING);
		this.kotaLahir = kotaLahir;
	}

	public Date getTglLahir() {
		return tglLahir;
	}

	public void setTglLahir(Date tglLahir) {
		doc.field(FUsr.TGL_LAHIR, tglLahir, OType.STRING);
		this.tglLahir = tglLahir;
	}

	public int getJenisKelamin() {
		return jenisKelamin;
	}

	public void setJenisKelamin(int jenisKelamin) {
		doc.field(FUsr.JENIS_KELAMIN, jenisKelamin, OType.INTEGER);
		this.jenisKelamin = jenisKelamin;
	}

	public String getNoTelp() {
		return noTelp;
	}

	public void setNoTelp(String noTelp) {
		doc.field(FUsr.NO_TELP, noTelp, OType.STRING);
		this.noTelp = noTelp;
	}

	public String getNoHp1() {
		return noHp1;
	}

	public void setNoHp1(String noHp1) {
		doc.field(FUsr.NO_HP1, noHp1, OType.STRING);
		this.noHp1 = noHp1;
	}

	public String getNoHp2() {
		return noHp2;
	}

	public void setNoHp2(String noHp2) {
		doc.field(FUsr.NO_HP2, noHp2, OType.STRING);
		this.noHp2 = noHp2;
	}

	public String getPinBb() {
		return pinBb;
	}

	public void setPinBb(String pinBb) {
		doc.field(FUsr.PIN_BB, pinBb, OType.STRING);
		this.pinBb = pinBb;
	}

	public Date getTglMasuk() {
		return tglMasuk;
	}

	public void setTglMasuk(Date tglMasuk) {
		doc.field(FUsr.TGL_MASUK, tglMasuk, OType.DATE);
		this.tglMasuk = tglMasuk;
	}

	public BigDecimal getGaji() {
		return gaji;
	}

	public void setGaji(BigDecimal gaji) {
		doc.field(FUsr.GAJI, gaji, OType.DECIMAL);
		this.gaji = gaji;
	}

	public JenisPekerjaan getJenisPekerjaan() {
		return jenisPekerjaan;
	}

	public void setJenisPekerjaan(JenisPekerjaan jenisPekerjaan) {
		doc.field(FUsr.JENIS_PEKERJAAN, jenisPekerjaan.getDoc(), OType.LINK);
		this.jenisPekerjaan = jenisPekerjaan;
	}
	
	public void setJenisPekerjaan(ODocument jenisPekerjaan) {
		doc.field(FUsr.JENIS_PEKERJAAN, jenisPekerjaan, OType.LINK);
		this.jenisPekerjaan = new JenisPekerjaan(jenisPekerjaan);
	}

	public String getPendidikanTerakhir() {
		return pendidikanTerakhir;
	}

	public void setPendidikanTerakhir(String pendidikanTerakhir) {
		doc.field(FUsr.PENDIDIKAN_TERAKHIR, pendidikanTerakhir, OType.STRING);
		this.pendidikanTerakhir = pendidikanTerakhir;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		doc.field(FUsr.STATUS, status, OType.STRING);
		this.status = status;
	}

	public ODocument getDoc() {
		return doc;
	}
	
	

	public String getCreateBy() {
		return createBy;
	}

	
	public void setCreateBy(ODocument createBy) {
		doc.field(FUsr.CREATE_BY, createBy, OType.LINK);
		this.createBy = createBy.field(FUsr.NAMA);
	}

	public String getUpdateBy() {
		return updateBy;
	}

	
	public void setUpdateBy(ODocument updateBy) {
		doc.field(FUsr.UPDATE_BY, updateBy, OType.LINK);
		this.updateBy = updateBy.field(FUsr.NAMA);
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		doc.field(FUsr.CREATE_AT, createAt, OType.DATETIME);
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		doc.field(FUsr.UPDATE_AT, updateAt, OType.DATETIME);
		this.updateAt = updateAt;
	}

	/**
	 * di sini mgisi grp juga
	 * @param doc
	 */
	public void setDoc(ODocument doc) {
		this.code = doc.field(FUsr.CODE);
		this.username = doc.field(FUsr.USERNAME);
		this.password = doc.field(FUsr.PASSWORD);
		
		ODocument tmp3=doc.field(FUsr.GRP);
		if (tmp3!=null) {
			this.grp =new Grp(tmp3); 
		}
		this.nama = doc.field(FUsr.NAMA);
		this.alamat = doc.field(FUsr.ALAMAT);
		this.kota = doc.field(FUsr.KOTA);
		this.noIdentitas = doc.field(FUsr.NO_IDENTITAS);
		this.jenisIdentitas = doc.field(FUsr.JENIS_IDENTITAS);
		this.kotaLahir = doc.field(FUsr.KOTA_LAHIR);
		this.tglLahir = doc.field(FUsr.TGL_LAHIR);
		Integer jenisKelaminObj=doc.field(FUsr.JENIS_KELAMIN);
		if (jenisKelaminObj==null) {
			this.jenisKelamin =0; 
		}else{
			this.jenisKelamin=jenisKelaminObj;
		}
		this.noTelp = doc.field(FUsr.NO_TELP);
		this.noHp1 = doc.field(FUsr.NO_HP1);
		this.noHp2 = doc.field(FUsr.NO_HP2);
		this.pinBb = doc.field(FUsr.PIN_BB);
		this.tglMasuk = doc.field(FUsr.TGL_MASUK);
		this.gaji = doc.field(FUsr.GAJI);
		ODocument jpdoc=doc.field(FUsr.JENIS_PEKERJAAN);
		if (jpdoc!=null) {
			this.jenisPekerjaan =new JenisPekerjaan(jpdoc); 
		}
		this.pendidikanTerakhir = doc.field(FUsr.PENDIDIKAN_TERAKHIR);
		
		Integer statusObj=doc.field(FUsr.STATUS);
		if (statusObj==null) {
			this.status =0; 
		}else{
			this.status=statusObj;
		}
		
		ODocument tmp=doc.field(FUsr.CREATE_BY);
		if (tmp!=null) {
			this.createBy=tmp.field(FUsr.NAMA);
		}
		
		ODocument tmp2=doc.field(FUsr.UPDATE_BY);
		if (tmp2!=null) {
			this.updateBy=tmp2.field(FUsr.NAMA);
		}
		
		this.createAt=doc.field(FUsr.CREATE_AT);
		this.updateAt=doc.field(FUsr.UPDATE_AT);
		this.doc = doc;
	}

}
