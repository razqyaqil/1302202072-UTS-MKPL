package lib;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;
	private String EmployeeName;
	
	private LocalDate dateJoined;
	private int monthWorkingInYear;
	
	private boolean isForeigner;
	private boolean isMarried;
	private boolean gender; //Laki-laki=true, Perempuan=false
	
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;

	private List<String> childNames;
	private List<String> childIdNumbers;
	
	private Employee(String employeeId, String EmployeeName, String dateJoined, boolean isForeigner, boolean isMarried, boolean gender) {
		this.employeeId = employeeId;
		this.EmployeeName = EmployeeName;
		this.isForeigner = isForeigner;
		this.isMarried = isMarried;
		this.gender = gender;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.dateJoined = LocalDate.parse(dateJoined, formatter);

		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}
	

	public void setMonthlySalary(int grade) {
		/**
		* Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya 
			* grade 1: 3.000.000 per bulan, 
			* grade 2: 5.000.000 per bulan, 
			* grade 3: 7.000.000 per bulan
		* Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
		*/	
		if (grade == 1) {
			monthlySalary = 3000000;
		}else if (grade == 2) {
			monthlySalary = 5000000;
		}else if (grade == 3) {
			monthlySalary = 7000000;
		}
		if (isForeigner) {
			monthlySalary = (int) (monthlySalary * 1.5);
		}
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	
	public int getMonthWorkingInYear() {	
		/*
		*Menghitung berapa lama pegawai bekerja dalam setahun ini, 
		*jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		*/
		LocalDate date = LocalDate.now();
		
		if (date.getYear() == dateJoined.getYear()) {
			return date.getMonthValue() - dateJoined.getMonthValue();
		}else {
			return 12;
		}
	}

	public int calculateTax() {	
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
		int TotalSalary = (monthlySalary+otherMonthlyIncome)*monthWorkingInYear;
		int numberOfChildren = childNames.size();
		int deductible = annualDeductible;

		int tax = (int) Math.round(0.05 * (TotalSalary - deductible - 54000000));

		if (numberOfChildren > 3) {
			numberOfChildren = 3;
		}
		
		if (isMarried) {
			tax = tax + 4500000 + (numberOfChildren * 1500000);
		}
		
		return tax;
			 
	}
}
