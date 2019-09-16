package sv.edu.bitlab.pupusap

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detalle_orde.*
import java.text.DecimalFormat

class DetalleOrdeActivity : AppCompatActivity(), PruebaFragment.PruebaFragmentListener {
  var arroz = arrayListOf<Int>()
  var maiz = arrayListOf<Int>()
  var handler=Handler()
  val FIVE_SECONDS=5000L
  val lineItemsIDs = arrayOf(
    arrayOf(R.id.lineItemDetail1, R.id.lineItemPrice1),
    arrayOf(R.id.lineItemDetail2, R.id.lineItemPrice2),
    arrayOf(R.id.lineItemDetail3, R.id.lineItemPrice3),
    arrayOf(R.id.lineItemDetail4, R.id.lineItemPrice4),
    arrayOf(R.id.lineItemDetail5, R.id.lineItemPrice5),
    arrayOf(R.id.lineItemDetail6, R.id.lineItemPrice6)
  )


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detalle_orde)
    Toast.makeText(this, "onCreate()", Toast.LENGTH_LONG).show()
    val params = this.intent.extras
    arroz = params!!.getIntegerArrayList(CONTADOR_ARROZ)!!
    maiz = params.getIntegerArrayList(CONTADOR_MAIZ)!!
    displayDetalle()


    Log.d("ACTIVITY", "onCreate()")
    //addFragment()
    button.setOnClickListener {
      handler.postDelayed({
        runOnUiThread {
          cargar()
        }
      }, FIVE_SECONDS)

      cargando.visibility=View.VISIBLE
    }

  }

  fun cargar(){
    //cargando.visibility=View.VISIBLE
    val intent2 = Intent(this, ordenRealizada::class.java)

  }

  fun displayDetalle() {
    val arr = arroz + maiz
    var total = 0.0f
    for((index, contador) in arr.withIndex()){
      val ids = lineItemsIDs[index]
      val detailTexview = findViewById<TextView>(ids[0])
      val priceTextView= findViewById<TextView>(ids[1])
      if(contador > 0){
        val totalUnidad = contador * VALOR_PUPUSA
        val descripcion = getDescripcion(index)
        detailTexview.text = getString(R.string.pupusa_line_item_description,
          contador, descripcion)
        total += totalUnidad
        val precio = DecimalFormat("$#0.00").format(totalUnidad)
        priceTextView.text = precio
      } else{
        detailTexview.visibility = View.GONE
        priceTextView.visibility = View.GONE
      }
    }
    val totalPrecio = findViewById<TextView>(R.id.lineItemPriceTotal)
    val precio = DecimalFormat("$#0.00").format(total)
    totalPrecio.text = precio

  }

 // fun addFragment() {
  //  val fragment = PruebaFragment.newInstance("Hola", "soy el frag")
   // val builder = supportFragmentManager
    //  .beginTransaction()
     // .add(R.id.fragment, fragment, FRAGMENT_TAG)
   // builder.commit()
 // }

  fun getDescripcion(index: Int): String {
    return when(index){
      QUESO -> "Queso de arroz"
      FRIJOLES -> "Frijol con queso de arroz"
      REVUELTAS -> "Revueltas de arroz"
      QUESO_MAIZ-> "Queso de maiz"
      FRIJOLES_MAIZ -> "Frijol con queso de maiz"
      REVUELTAS_MAIZ -> "Revueltas de maiz"
      else -> throw RuntimeException("Pupusa no soportada")
    }
  }


  //region DetalleOrdeActivity
  override fun onFragmentInteraction(uri: Uri) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
  //endregion

  companion object{
    const val QUESO = 0//3
    const val FRIJOLES = 1//4
    const val REVUELTAS = 2//5
    const val QUESO_MAIZ = 3//3
    const val FRIJOLES_MAIZ = 4//4
    const val REVUELTAS_MAIZ = 5//5
    const val CONTADOR_ARROZ = "ARROZ"
    const val CONTADOR_MAIZ = "MAIZ"
    const val VALOR_PUPUSA = 0.5F
    const val FRAGMENT_TAG = "FRAGMENT_TAG"
  }
}
