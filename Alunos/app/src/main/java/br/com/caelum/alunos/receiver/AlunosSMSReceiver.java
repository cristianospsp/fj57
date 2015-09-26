package br.com.caelum.alunos.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.caelum.alunos.dao.AlunoDAO;

/**
 * Created by android5243 on 26/09/15.
 */
public class AlunosSMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        AlunoDAO alunoDAO = new AlunoDAO(context);

        Bundle bundle = intent.getExtras();

        Object[] mensagens = (Object[]) bundle.get("pdus");

        byte[] mensagen = (byte[]) mensagens[0];

        SmsMessage fromPdu = SmsMessage.createFromPdu(mensagen);
        String telefoneAluno = fromPdu.getDisplayOriginatingAddress();

        if (alunoDAO.isAluno(telefoneAluno)) {
            Toast.makeText(context, "Chegou um SMS de: " + telefoneAluno, Toast.LENGTH_LONG).show();
        }

    }

}
