/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buccirenzettimessaggisticaclient;

import java.util.Scanner;

/**
 *
 * @author renzetti.alessandro
 */
public class InvioMessaggi {
    public String chiediEMandaMessaggio() {
        Scanner s = new Scanner(System.in);
        System.out.print("Cosa vuoi scrivere al client?: ");
        return s.nextLine();
    }
}
