package mx.tecnm.tepic.ladm_u2_ejercicio1_timerhilos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var contadorTimer=0
    var ContadorHilo =0
    val Lapso = 2000 //2000 milisegundos=2segndo
    val TiempoTotal = 20000 //20,000 milisegundos=20 segundo
    var hilito = hilo(this)
    //Sintaxis del timer  CountDownTimer(TiempoTotal,Lapso en milisegundos)
    var timer = object : CountDownTimer(TiempoTotal.toLong(),Lapso.toLong()){
        override fun onTick(p0: Long) {
            /*
            * On Tick se ejecuta cuando el tiepo "LAPSO" llega a ser o
            * */
            contadorTimer ++
            textView.text="Timer: ${contadorTimer}"
        }

        override fun onFinish() {
            /*
            * On Finish= se ejecuta cuando el tiempo "TOTAL" se hace 0
            * una vez que se ejecuta el onFinish el timer se detiene o a MENOS
            * A MENOS que se invoque un START dentro del inFinish empezando de nuevo
            * */
            start()
        }

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            timer.start()
        }
        button2.setOnClickListener {
            hilito.start() /*hago que el hilito contruido apartir de clase HILO
                            se ejecute en segundo plano*/
        }
    }
}

//CLASE HILO
class hilo(p:MainActivity) :Thread(){
    var puntero=p
    override fun run() {
        super.run()
        /*
        * Metodo que funciona similar al ONTICK es decir SIEMPRE EN EJECUCION
        * SIEMPRE  que se cicle
        * RUN solo se ejecuta una ves en 2 plano.
        * */
        while (true){
            puntero.ContadorHilo ++
            puntero.runOnUiThread{
                /*se debe se usar el runOnUiThread debido a que la interfaz grafica
                *le PERTENECE al MainActivity y jotlin no permite que de MANERA DIRECTA
                * OTRA CLASE modifique una interfaz grafica que no le pertenece
                * La otra clase se entiende que es el HILO. por ello usamos
                * el bloque runOnUiThread para aplicar permisos de modificacion
                *  */
                puntero.textView2.text="Hilo:"+puntero.ContadorHilo
            }
            // al hilo le afecta el estres
            sleep(100)
            //CORROTINuAS ASUNCTASK  = CLSE DEPRECIDA = AsyncTask(Obsoleta)

        }

    }
}

