package com.ug9.eTransactionProject;

public class MobileWallet extends DigitalPayment {
    private String noHP;
    protected long feeTransferBank;

    public MobileWallet(String nama, long saldo, String noHp) {
        super(nama, saldo);
        this.noHP = noHp;
    }

    @Override
    public void transfer(DigitalPayment dp, long nominal) {
        if (nominal <= 0) {
            System.out.println("Nominal yang Anda input tidak valid!");
            return;
        }
        if (getSaldo() < nominal) {
            System.out.println("Transfer gagal! Saldo Anda tidak mencukupi.");
            return;
        }
        if (dp instanceof BRImo || dp instanceof BNImo) {
            if (getSaldo() < nominal + feeTransferBank) {
                System.out.println("Transfer gagal! Saldo Anda tidak mencukupi.");
                return;
            }
            setSaldo(getSaldo() - (nominal + feeTransferBank));
        } else {
            setSaldo(getSaldo() - nominal);
        }
        dp.setSaldo(dp.getSaldo() + nominal);
        printBuktiTransfer(dp, nominal);
    }
}