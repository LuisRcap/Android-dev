package com.example.examen1;

public class Obj {
    String figura;
    float rho, theta=0.3F, phi=1.3F, d, objSize;
    float v11, v12, v13, v21, v22, v23, v32, v33, v43; // elementos de la matriz V


    Point3D [] w;	// coordenadas universales
    Point2D [] vScr; // coordenadas de la pantalla
    public Obj(int vector1[], int vector2[], int vector3[]){

        w = new Point3D[8];
        vScr = new Point2D[8];
        w[0] = new Point3D(0, 0, 0); // desde la base
        w[1] = new Point3D(vector1[0], vector1[1], vector1[2]);
        w[2] = new Point3D(vector1[0] + vector2[0], vector1[1] + vector2[1], vector1[2] + vector2[2]);
        w[3] = new Point3D(vector2[0], vector2[1], vector2[2]);
        w[4] = new Point3D(vector3[0], vector3[1], vector3[2]);
        w[5] = new Point3D(vector1[0] + vector3[0], vector1[1] + vector3[1], vector1[2] + vector3[2]);
        w[6] = new Point3D(vector1[0] + vector2[0] + vector3[0], vector1[1] + vector2[1] + vector3[1], vector1[2] + vector2[2] + vector3[2]);
        w[7] = new Point3D(vector2[0] + vector3[0], vector2[1] + vector3[1], vector2[2] + vector3[2]);


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
        int numeroVertices = 8;

        for(int i=0; i<numeroVertices; i++){
            Point3D p = w[i];
            float x = v11*p.x + v21*p.y, y = v12*p.x + v22*p.y + v32*p.z, z = v13*p.x + v23*p.y + v33*p.z + v43;
            vScr[i] = new Point2D(-d*x/z, -d*y/z);
        }

    }
}
