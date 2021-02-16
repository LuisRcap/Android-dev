package com.example.figuras3d;

public class Obj {
    String figura;
    float rho, theta=0.3F, phi=1.3F, d, objSize;
    float v11, v12, v13, v21, v22, v23, v32, v33, v43; // elementos de la matriz V
    Point3D [] w;	// coordenadas universales
    Point2D [] vScr; // coordenadas de la pantalla
    public Obj(String fig){
        figura = fig;
        if(figura.equals("Cubo3D")){
            w = new Point3D[8];
            vScr = new Point2D[8];
            w[0]	= new Point3D(1, -1, -1); // desde la base
            w[1]	= new Point3D(1, 1, -1);
            w[2]	= new Point3D(-1, 1, -1);
            w[3]	= new Point3D(-1, -1, -1);
            w[4]	= new Point3D(1, -1, 1);
            w[5]	= new Point3D(1, 1, 1);
            w[6]	= new Point3D(-1, 1, 1);
            w[7]	= new Point3D(-1, -1, 1);
        }
        else if(figura.equals("Piramide3D")){
            w = new Point3D[8];
            vScr = new Point2D[8];

            w[0] = new Point3D(1, -1, -1);
            w[1] = new Point3D(1, 1, -1);
            w[2] = new Point3D(-1, 1, -1);
            w[3] = new Point3D(-1, -1, -1);
            w[4] = new Point3D(0, 0, 1);
            w[5] = new Point3D(1, 1, 1);
            w[6] = new Point3D(-1, 1, 1);
            w[7] = new Point3D(-1, -1, 1);
        }
        else if(figura.equals("Prisma3D")){
            w = new Point3D[8];
            vScr = new Point2D[8];
            w[0] = new Point3D(1, -1, -1);
            w[1] = new Point3D(1, 1, -1);
            w[2] = new Point3D(-1, 1, -1);
            w[3] = new Point3D(-1, -1, -1);
            w[4] = new Point3D(1, -1, 1.5);
            w[5] = new Point3D(1, 1, 1.5);
            w[6] = new Point3D(-1, 1, 1.5);
            w[7] = new Point3D(-1, -1, 1.5);
        }
        else if(figura.equals("Cono3D")){
            w = new Point3D[73];
            vScr = new Point2D[73];
            double i = 0;
            for (int j=0; j < 72; i+=0.087, j++) {
                w[j] = new Point3D(Math.cos(i), Math.sin(i), -1);
            }
            w[72] = new Point3D(0, 0, 1);
        }
        else if(figura.equals("Esfera3D")){
            w = new Point3D[4608];
            vScr = new Point2D[4608];
            double i;
            double altura = 1;
            double y = -0.99;
            double anchura;
            for (int k = 0; k < 4608; k+=72) {
                i = 0;
                anchura =Math.sqrt(1-(y*y));
                for (int j=0; j < 36; i+=0.171, j+=1) {
                    w[j+k] = new Point3D(anchura*Math.cos(i), anchura*Math.sin(i), -altura);
                    w[j+36+k] = new Point3D(anchura*Math.cos(i), anchura*Math.sin(i), altura);
                }
                y+=0.015;
                altura-=0.016;
            }
        }
        else if(figura.equals("Cilindro3D")){
            w = new Point3D[144];
            vScr = new Point2D[144];

            double i = 0;
            for (int j=0; j < 144; i+=0.087, j+=2) {
                w[j] = new Point3D(Math.cos(i), Math.sin(i), -1);
                w[j+1] = new Point3D(Math.cos(i), Math.sin(i), 1);
            }
        }
        objSize = (float) Math.sqrt(12F);
        rho = 5*objSize;
    }

    void initPersp(){
        float costh = (float)Math.cos(theta), sinth=(float)Math.sin(theta);
        float cosph=(float)Math.cos(phi), sinph=(float)Math.sin(phi);
        v11 = -sinth;
        v12 = -cosph*costh;
        v13 = sinph*costh;
        v21 = costh;
        v22 = -cosph*sinth;
        v23 = sinph*sinth;
        v32 = sinph;
        v33 = cosph;
        v43 = -rho;
    }
    void eyeAndScreen(){
        initPersp();
        int numeroVertices = 0;
        if(figura.equals("Cubo3D")){
            numeroVertices = 8;
        }
        else if(figura.equals("Piramide3D")){
            numeroVertices = 8;
        }
        else if(figura.equals("Prisma3D")){
            numeroVertices = 8;
        }
        else if(figura.equals("Cono3D")){
            numeroVertices = 73;
        }
        else if(figura.equals("Esfera3D")){
           numeroVertices = 4608;
        }
        else if(figura.equals("Cilindro3D")){
            numeroVertices = 144;
        }
        for(int i=0; i<numeroVertices; i++){
            Point3D p = w[i];
            float x = v11*p.x + v21*p.y, y = v12*p.x + v22*p.y + v32*p.z, z = v13*p.x + v23*p.y + v33*p.z + v43;
            vScr[i] = new Point2D(-d*x/z, -d*y/z);
        }

    }
}
