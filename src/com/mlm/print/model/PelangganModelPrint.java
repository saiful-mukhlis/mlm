package com.mlm.print.model;

import java.util.List;

public class PelangganModelPrint {
	private String code;
	private String namaToko;
	private String namaPemilik;
	private String jenisIdentitas;
	private String noIdentitas;
	private String alamat;
	private String kota;
	private String noTelp;
	private String noFax;
	private String noHp1;
	private String noHp2;
	private String pinBb1;
	private String pinBb2;
	private String status;
	
	private List<PpModelPrint> pps;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNamaToko() {
		return namaToko;
	}

	public void setNamaToko(String namaToko) {
		this.namaToko = namaToko;
	}

	public String getNamaPemilik() {
		return namaPemilik;
	}

	public void setNamaPemilik(String namaPemilik) {
		this.namaPemilik = namaPemilik;
	}

	public String getJenisIdentitas() {
		return jenisIdentitas;
	}

	public void setJenisIdentitas(String jenisIdentitas) {
		this.jenisIdentitas = jenisIdentitas;
	}

	public String getNoIdentitas() {
		return noIdentitas;
	}

	public void setNoIdentitas(String noIdentitas) {
		this.noIdentitas = noIdentitas;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getKota() {
		return kota;
	}

	public void setKota(String kota) {
		this.kota = kota;
	}

	public String getNoTelp() {
		return noTelp;
	}

	public void setNoTelp(String noTelp) {
		this.noTelp = noTelp;
	}

	public String getNoFax() {
		return noFax;
	}

	public void setNoFax(String noFax) {
		this.noFax = noFax;
	}

	public String getNoHp1() {
		return noHp1;
	}

	public void setNoHp1(String noHp1) {
		this.noHp1 = noHp1;
	}

	public String getNoHp2() {
		return noHp2;
	}

	public void setNoHp2(String noHp2) {
		this.noHp2 = noHp2;
	}

	public String getPinBb1() {
		return pinBb1;
	}

	public void setPinBb1(String pinBb1) {
		this.pinBb1 = pinBb1;
	}

	public String getPinBb2() {
		return pinBb2;
	}

	public void setPinBb2(String pinBb2) {
		this.pinBb2 = pinBb2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<PpModelPrint> getPps() {
		return pps;
	}

	public void setPps(List<PpModelPrint> pps) {
		this.pps = pps;
	}

	
}
