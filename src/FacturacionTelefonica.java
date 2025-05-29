import java.util.Scanner;

public class FacturacionTelefonica {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de líneas telefónicas: ");
        int cantidadLineas = sc.nextInt();

        //Chequear que cantidad de lineas sea mayor a 0
        while (cantidadLineas <= 0) {
            System.out.print("La cantidad debe ser mayor a 0. Ingrese nuevamente: ");
            cantidadLineas = sc.nextInt();
        }

        double[][] datosLineas = getUserData(cantidadLineas, sc);

        processBills(datosLineas);

        sc.close();
    }

    public static double[][] getUserData(int cantidadLineas, Scanner sc) {
        
        double[][] lineas = new double[cantidadLineas][2];

        for (int i = 0; i < cantidadLineas; i++) {

            System.out.println("Línea #" + (i + 1));

            double base;
            do {
                System.out.print("Costo base del plan: ");
                base = sc.nextDouble();
                if (base < 0) {
                    System.out.println("El valor no puede ser negativo.");
                }
            } while (base < 0);

            double minutos;
            do {
                System.out.print("Minutos excedidos: ");
                minutos = sc.nextDouble();
                if (minutos < 0) {
                    System.out.println("El valor no puede ser negativo.");
                }
            } while (minutos < 0);
//
            lineas[i][0] = base;
            lineas[i][1] = minutos;
        }

        return lineas;
    }

    public static double calculateOverages(double minutos) {
        return minutos * 0.25;
    }

    public static double calculateTax(double subtotal) {
        return subtotal * 0.15;
    }

    public static void calculateAndPrintBill(double base, double overage, double tax) {

        double total = base + overage + tax;

        System.out.println("Costo base: $" + redondear2Decimales(base));
        System.out.println("Cargo por excedente: $" + redondear2Decimales(overage));
        System.out.println("Impuesto (15%): $" + redondear2Decimales(tax));
        System.out.println("Total a pagar: $" + redondear2Decimales(total));
        System.out.println("-----------------------------");
    }

    public static void processBills(double[][] lineas) {

        double totalGeneral = 0;
        //
        int index = 1;

        for (double[] linea : lineas) {

            System.out.println("Factura Línea #" + index);

            double base = linea[0];
            double minutos = linea[1];

            double overage = calculateOverages(minutos);
            double subtotal = base + overage;
            double tax = calculateTax(subtotal);
            double total = base + overage + tax;

            calculateAndPrintBill(base, overage, tax);

            totalGeneral += total;
            index++;
        }

        System.out.println("Total general a pagar por la empresa: $" + redondear2Decimales(totalGeneral));
        System.out.println("Promedio por línea: $" + redondear2Decimales(totalGeneral / lineas.length));
    }

    public static double redondear2Decimales(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
