
public class TestMatrix {
	public static void main(String args[]) {
		MatrixTool r=new MatrixTool();
		double[][] A= new double[3][3];
		int k=1;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				A[i][j]=k;
				k++;
			}
		}
		
		//test trace trace()
		double tr=r.trace(A);
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				System.out.print(A[i][j]+" ");
			}
			System.out.println();
		}
		
		System.out.println("Traccia di A: "+tr);
		
		
		
		//test transposed t()
		double[][] At=new double[3][3];
		At=r.t(A);
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				System.out.print(At[i][j]+" ");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		//test matrix product prod(A,B)
		double[][] B=new double[3][3];
		B[0][0]=3;B[0][1]=5;B[0][2]=5;
		B[1][0]=0;B[1][1]=1;B[1][2]=5;
		B[2][0]=5;B[2][1]=5;B[2][2]=5;
		double [][]C=new double[3][3];
		C=r.prod(A, B);
		double trb=r.trace(B);
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				System.out.print(C[i][j]+" ");
			}
			System.out.println();
		}
		
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		
		//test matrix-vector prod(A,v)
		double[] v=new double[3];
		v[1]=3;v[2]=2;v[0]=0;
		double[] x=new double[3];
		x=r.prod(A, v);
		for(int i=0; i<3; i++) {
			System.out.print(x[i]+" ");
		}
		
				
		System.out.println();
		System.out.println();
		System.out.println();
		
		double[][] U=new double[3][3];
		U=r.gausseli(A);
		System.out.println("U=");
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				System.out.print(U[i][j]+" ");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		//test scalar product
		double b=0;
		b=r.scalar(x,A,v);
		System.out.println(b);
				
	}

}
