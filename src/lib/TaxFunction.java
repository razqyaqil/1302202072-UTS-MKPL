package lib;

public class TaxFunction {

	
	/**
	 * Menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan 
	 * total gaji selama bulan bekerja dikurangi penghasilan tidak kena pajak
	 * 
	 * penghasilan tidak kena pajaknya pegawai adalah Rp 54.000.000
	 * jika pegawai menikah penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * jika pegawai memiliki anak pajaknya ditambah sebesar Rp 1.500.000/anak.
	 * maksimal penambahan anak adalah 3.
	 * 
	 */
	
	
	public static int calculateTax(int Salary, int deductible, boolean isMarried, int numberOfChildren) {
		
		int tax = (int) Math.round(0.05 * (Salary - deductible - 54000000));

		if (numberOfChildren > 3) {
			numberOfChildren = 3;
		}
		
		if (isMarried) {
			tax = tax + 4500000 + (numberOfChildren * 1500000);
		}
		
		return tax;
			 
	}
	
}
