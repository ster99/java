/**
This class contains all the method for linear algebra operations with matrices and vectors.
Read the documentation to know more about every command.
*/
public class MatrixTool {
				
		/**
		It verifies that the matrix is squared then it applies the GAUSSIAN ELIMINATION.
		This give us an upper triangular matrix. It's easy to prove that the determinant 
		of this kind of matrix is the product of the elements on the diagonal.

		@param The only parameter needed is the matrix (2-dim array) of which you want the determinant.
		@return The method return the determinant of a given matrix A as a double 2-dimensional array.*/
		
		public double det(double[][] A){
			double det=1;
			if(A.length!=A[0].length){
				System.out.println("Ops! The given matrix isn't squared. It will be assigned the deafult value 0.");
				det=0;
			}
			else{
				int n=A.length;
				double[][] U=new double[n][n];
				U=gausseli(A);
				for(int i=0; i<n; i++)
					det*=U[i][i];
			}
			return det;
		}
		
	
		
		/**
		It verifies that the matrix is squared then it applies the GAUSSIAN ELIMINATION.
		This give us an upper triangular matrix. 

		@param The only parameter needed is the matrix (2-dim array).
		@return The method return the upper triangular matrix associated with A.*/
		
		public double[][] gausseli(double[][] A){
			int n=A[0].length;
			int flag=0;
			double tmp=0;
			double m=0;
			for(int k=0; k<n-1; k++) {
				flag=k;
				if(A[k][k]==0) {
					while(A[flag][k]==0) {
						flag+=1;
					}
					for(int h=0; h<n; h++) {
						tmp=A[flag][h];
						A[flag][h]=A[k][h];
						A[k][h]=tmp;
					}	
				}
				for(int i=k+1; i<n; i++) {
					m=A[i][k]/A[k][k];
					for(int j=k; j<n; j++) {
						A[i][j]-=m*A[k][j];
					}
				}
			}
			return A;
		}
	
	
		/**
		This method is helpful for the resolution of linear systems of equations.
		The method verify the compatibility of the dimension between A and b.
		Then it solves the linear system Ax=b.

		@param In input are given the matrix A (2-dim array) and b that stands for the vector of constant terms.
		@return solvesys() return the solution x of the linear system Ax=b.*/
	
		public double[] solvesys(double[][] A, double[] b) {
			int n=A.length;
			double[][] U=new double[n][n];
			double[] x=new double[n];
			U=gausseli(A);
			x=solveupper(U,b);
			return x;
		}
		
	
	
		/**
		It is similar to the method before (see solvesys() for more details).
		It make the exactly same process but the constant term given is a matrix B.
		It means that it sees "length of B"-number of linear systems with the same coefficients matrix A
		and different vector of constant terms (every column vector of B). It gives back the matrix X where
		every column of X, X(i), is the solution ov the linear system AX(i)=B(i).

		@param In input are given the matrices A and B (2-dim array). The first is the matrix of coefficients,
		then the second is the matrix of constant terms.
		@return solvemultisys() return the solution matrix X of the linear multi-system AX=B.*/
	
		public double[][] solvemultisys(double[][] A, double[][] B) {
			int n=A.length;
			double[][] X=new double[n][n];
			double[] x=new double[n];
			double[] b=new double[n];
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					b[j]=B[j][i];
				}
				x=solvesys(A,b);
				for(int k=0; k<n; k++) {
					X[k][i]=x[k];
				}
				for(int h=0; h<n; h++) {
					x[h]=0;
					b[h]=0;
				}
			}
			return X;
		}
		
		
		/**
		This is the method to solve back a linear system Ax=b after the gaussian elimination.
		The system will be like Ux=b* where U is an upper triangular matrix and b* is the vector
		of constant terms modified according to gaussian elimination as well.

		@param In input are given the matrices A and B (2-dim array). The first is the matrix of coefficients,
		then the second is the matrix of constant terms.
		@return solvemultisys() return the solution matrix X of the linear multi-system AX=B.*/
	
		public double[] solveupper(double U[][], double[] b) {
			int n=b.length;
			double[] x=new double[n];
			double sum=0;
			x[n-1]=b[n-1]/U[n-1][n-1];
			
			
			for(int i=n-2; i>0; i--) {
				for(int j=i+1; j<n; j++) {
					sum+=U[i][j]*x[j];
				}
				x[i]=(b[i]-sum)/U[i][i];
				sum=0;
			}
			return x;
		}
		//
		//
		//
		public double[] solvelower(double L[][], double[] b) {
			int n=b.length;
			double[] x=new double[n];
			double sum=0;
			x[0]=b[0]/L[1][1];
			for(int i=1; i<n; i++) {
				for(int j=0; j<i; j++) {
					sum+=L[i][j]*x[j];
				}
				x[i]=(b[i]-sum)/L[i][i];
				sum=0;
			}
			return x;
		}
		//
		//
		//
		public double[][] t (double[][] A){
			int n=A.length;
			double[][] At=new double[n][n];
			for(int i=0; i<n; i++){
				for(int j=0; j<n; j++){
						At[i][j]=A[j][i];
				}
			}
			return At;
		}	
		//
		//
		//
		public double[][] inverse(double[][] A){
			int n=A.length;
			double[][] B=new double[n][n];
			B=solvemultisys(A,id(n));
			return B;
		}
		//
		//<
		//
		public double trace(double[][] A){
			double tr=0;
			int n=A.length;
			for(int i=0; i<n;i++)
				tr+=A[i][i];
			
			return tr;
		}
		//
		//
		//
		public double[][] prod(double[][] A, double[][] B){
			int n=A.length;
			int m=B[0].length;
			double[][] C=new double[n][m];
			for(int h=0; h<n; h++) {
				for(int l=0; l<m; l++) {
					C[h][l]=0;
				}
			}
			if(A.length==B[0].length) {
				for(int i=0; i<n; i++) {
					for(int j=0; j<n; j++) {
						for(int k=0; k<A[0].length; k++) {
							C[i][j]+=A[i][k]*B[k][j];
						}
					}
				}
			}else {
				System.out.println("Ops. It occurred an error in the dimensions of given matrix.");
				System.out.println("Make sure that the number of column of the first matrix is the same amount of the rows of the second matrix");
				System.out.println("The product matrix has been setted to default null Mat("+n+"x"+m+").");
				}
			return C;
			
		}
		//
		//
		//
		public double[] prod(double[][] A, double[] v){
			int n=A[0].length;
			double[] x=new double[n];
			for(int h=0; h<n; h++) {
					x[h]=0;
				}
			if(A.length==v.length) {
				for(int i=0; i<n; i++) {
					for(int j=0; j<n; j++) {
							x[i]+=A[i][j]*v[j];
						}
					}
			}else {
				System.out.println("Ops. It occurred an error in the dimensions of given vector or matrix.");
				System.out.println("Make sure that the number of column of the matrix is the same amount of the rows of the vector");
				System.out.println("The product vector has been setted to default null vect("+n+").");
				}
			return x;
			
			
		}
		//
		//
		//
		public double prod(double[] v, double[] w){
			double a=0;
			if(v.length==w.length) {
				for(int i=0; i<v.length; i++) {
					a+=v[i]*w[i];
						}
			}else {
				System.out.println("Ops. It occurred an error in the dimensions of given vectors.");
				System.out.println("Make sure that the number of rows is the same for both vectors");
				System.out.println("The scalar has been setted to default value 0.");
				}
			return a;
			
			
		}
		//
		//
		//
		public double scalar(double[] v, double[][] A, double[] w){
			double a=prod(v,prod(A,w));
			return a;
		}
		//
		//
		//
		public double norm(double[] x, int p) {
			double q=0;
			double sum=0;
			for(int i=0; i<x.length; i++) 
				sum+=Math.pow(x[i], p);
			
			q=Math.pow(sum, 1/p);
			return q;
		}
		//
		//
		//
		public double norm(double[] x) {
			double q=norm(x,2);
			return q;
		}
		//
		//
		//
		public double[][] diag(int n, int d) {
			double[][] D=new double[n][n];
			for(int i=0; i<n; i++) 
				D[i][i]=d;
			return D;
		}
		//
		//
		//
		public double[][] id(int n){
			double[][] I=new double[n][n];
			I=diag(n,1);
			return I;
		}
		//
		//
		//
		public double[] zeros(int c) {
			double[] v=new double[c];
			for(int i=0; i<c; i++) {
				v[i]=0;
			}
			return v;
		}
		//
		//
		//
		public double[] ones(int c) {
			double[] v=new double[c];
			for(int i=0; i<c; i++) {
				v[i]=1;
			}
			return v;
		}
		//
		//
		//
		public double[][] zeros(int r, int c){
			double[][] A=new double[r][c];
			for(int i=0; i<r; i++) {
				for(int j=0; j<c; j++) {
				A[i][j]=0;
				}
			}
			return A;
		}
		//
		//
		//
		public double[][] ones(int r, int c){
			double[][] A=new double[r][c];
			for(int i=0; i<r; i++) {
				for(int j=0; j<c; j++) {
				A[i][j]=1;
				}
			}
			return A;
		}
		//
		//
		//
		public double[][] triu(int n, int a){
			double[][] A=new double[n][n];
			for(int i=0; i<n; i++) {
				for(int j=i; j<n; j++) {
					A[i][j]=a;
				}
			}
			return A;	
		}
		//
		//
		//
		public double[][] tril(int n, int a){
			double[][] A=new double[n][n];
			for(int i=0; i<n; i++) {
				for(int j=0; j<i+1; j++) {
					A[i][j]=a;
				}
			}
			return A;
		}
		//
		//
		//
		public double[][] triu(double[][] A){
			for(int i=0; i<A.length; i++) {
				for(int j=0; j<i; j++) {
					A[i][j]=0;
				}
			}
			return A;
		}
		//
		//
		//
		public double[][] tril(double[][] A){
			for(int i=0; i<A.length; i++) {
				for(int j=i+1; j<A.length; j++) {
					A[i][j]=0;
				}
			}
			return A;
		}
		//
		//
		//
		public double[] sort(double[] v, int p) {
			double tmp=0;
			if(p==1) {
				for(int i=0; i<v.length; i++){
					for(int j=0; j<v.length; j++) {
						if(v[j]<v[i]) {
							tmp=v[j];
							v[j]=v[i];
							v[i]=tmp;
						}
					}
				}
				
			}else if(p==-1) {
				for(int i=0; i<v.length; i++){
					for(int j=0; j<v.length; j++) {
						if(v[j]>v[i]) {
							tmp=v[j];
							v[j]=v[i];
							v[i]=tmp;
						}
					}
				}
			}
			return v;
		}
}
