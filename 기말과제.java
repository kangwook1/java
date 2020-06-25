import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class 기말과제 {
	public static int fx(int i,int[] x,int[] a,int[] b, int[] py) {
		int mse = 0;
		int j=i;
		for(i=0;i<x.length;i++) {
			py[i]= (a[i]*x[j])-b[i];
			py[i]= py[i]*py[i];
			mse += py[i];
		}
		mse= mse/x.length;
		return mse; 
	}
	
	
	public static int[] init() {
		Random r = new Random();
		int[] arr= new int[8];
		for(int i=0; i<8; i++) {
			arr[i] = r.nextInt(9+1);
			System.out.printf("%d ", arr[i]);
		}
		System.out.println();
		
		return arr;
	
}

public static int[] selection(int[] x,int[] a,int[] b, int[] py) {
	int sum=0;
	int[] f= new int[x.length];
	for(int i=0; i<x.length;i++) {
		f[i]= fx(i,x,a,b,py);
		sum +=f[i];
	}
	
	double[] ratio= new double[x.length];
	for(int i=0; i<x.length;i++) {
		if(i==0)
			ratio[i]=(double)f[i]/(double)sum;
		else
			ratio[i]= ratio[i-1]+ (double)f[i]/(double)sum;
	}
	
	int[] sx= new int[x.length];
	Random r= new Random();
	for(int i=0; i<x.length;i++) {
		double p= r.nextDouble();
		if(p < ratio[0])
			sx[i]=x[0];
		else if(p < ratio[1])
			sx[i]=x[1];
		else if(p < ratio[2])
			sx[i]=x[2];
		else if(p < ratio[3])
			sx[i]=x[3];
		else if(p < ratio[4])
			sx[i]=x[4];
		else if(p < ratio[5])
			sx[i]=x[5];
		else if(p < ratio[6])
			sx[i]=x[6];
		else 
			sx[i]= x[7];
		
	}
	return sx;
		
}

public static String int2String(String x) {
	return String.format("%5s", x).replace(' ', '0');
}

public static String[] crossOver(int[] x){
	String[] bitarr = new String[x.length];
	for(int i=0; i<x.length;i+=2)
	{
	String bit1 = int2String(Integer.toBinaryString(x[i]));
	String bit2 = int2String(Integer.toBinaryString(x[i+1]));
	
	bitarr[i] = bit1.substring(0,2) + bit2.substring(2,5);
	bitarr[i+1] = bit2.substring(0,2) + bit1.substring(2,5);
	}
	return bitarr;
	
}

public static int invert(String x) {
	Random r= new Random();
	int a = Integer.parseInt(x,2);
	for(int i=0; i<x.length();i++) {
		double p = (double)1/(double)32;
		if(r.nextDouble()< p)
			a = 1 << i ^ a;
	
	}
	return a;
}

public static int[] mutation(String[] cx) {
	int[] arr= new int[cx.length];
	for(int i=0; i<cx.length; i++) {
		arr[i]= invert(cx[i]);
	}
	return arr;
}

   public static void main(String[] args) {
	int[] x= init(); // y=ax의 기울기
	int[] a= {57,59,60,61,63,64,66,67}; //데이터(몸무게)
	int[] b= {167,169,171,173,175,177,179,181}; //데이터(키)
	int[] py= new int[8];
	
	int min= 1000000;
	
	for(int i=0; i<30; i++) {
	 int[] sx= selection(x,a,b,py);
	 String[] cx= crossOver(sx);
	 int[] mx= mutation(cx);
	
	 for(int k=0;k<x.length;k++) {
		 System.out.printf("%5d ", mx[k]);
	 }
	 System.out.println();
	 int[] f= new int[mx.length];
	 for(int j=0; j<mx.length;j++) {
		 f[j]=fx(j,mx,a,b,py);
		 System.out.printf("%d ", f[j]);
		 min = Math.min(min, f[j]);
	 }
	
	System.out.println();
	System.out.println(min);
	}	
	
}
}
