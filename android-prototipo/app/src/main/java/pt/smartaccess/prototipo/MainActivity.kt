package pt.smartaccess.prototipo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Prototipo muito basico: dados inventados, sem API e sem guardar nada
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(Modifier.fillMaxSize()) {
                    Column(Modifier.safeDrawingPadding().padding(16.dp)) {
                        Prototipo()
                    }
                }
            }
        }
    }
}

@Composable
fun Prototipo() {
    var ecra by remember { mutableStateOf("login") }

    when (ecra) {
        "login" -> Login(aoEntrar = { ecra = "portas" })
        "portas" -> Portas(aoVerCartoes = { ecra = "cartoes" }, aoSair = { ecra = "login" })
        "cartoes" -> Cartoes(aoVoltar = { ecra = "portas" })
    }
}

@Composable
fun Login(aoEntrar: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Text("SmartAccess")
    OutlinedTextField(email, { email = it }, label = { Text("Email") })
    OutlinedTextField(password, { password = it }, label = { Text("Palavra-passe") })
    Button(onClick = aoEntrar) { Text("Entrar") }
}

@Composable
fun Portas(aoVerCartoes: () -> Unit, aoSair: () -> Unit) {
    var aberta by remember { mutableStateOf(false) }

    Text("Portas")
    HorizontalDivider()
    Text("Sala 1.04")
    Text(if (aberta) "Aberta" else "Trancada")
    Text("Pessoas dentro: 0")
    Button(onClick = { aberta = !aberta }) { Text(if (aberta) "Fechar" else "Abrir") }
    HorizontalDivider()
    Row {
        Button(onClick = aoVerCartoes) { Text("Cartoes") }
        Button(onClick = aoSair) { Text("Sair") }
    }
}

@Composable
fun Cartoes(aoVoltar: () -> Unit) {
    val cartoes = remember { mutableStateListOf("8DF36E69 - Administrador", "337A93AA - Docente Teste") }
    var uid by remember { mutableStateOf("") }
    var nome by remember { mutableStateOf("") }

    Text("Cartoes")
    HorizontalDivider()
    cartoes.forEach { Text(it) }
    HorizontalDivider()
    OutlinedTextField(uid, { uid = it }, label = { Text("UID") })
    OutlinedTextField(nome, { nome = it }, label = { Text("Nome") })
    Button(onClick = {
        cartoes.add("$uid - $nome")
        uid = ""
        nome = ""
    }) { Text("Adicionar") }
    Button(onClick = aoVoltar) { Text("Voltar") }
}
