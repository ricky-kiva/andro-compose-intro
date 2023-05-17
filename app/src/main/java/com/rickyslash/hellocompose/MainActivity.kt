package com.rickyslash.hellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rickyslash.hellocompose.ui.theme.HelloComposeTheme

private val sampleName = listOf(
    "Guts",
    "Casca",
    "Puck",
    "Griffith",
    "Ishidro",
    "Farnese",
    "Serpico",
    "Schirke"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // this to deploy composable function on Activity
        setContent {
            // this to set the 'theme' of the Compose
            HelloComposeTheme {
                // call the custom Composable Function that has been made
                HelloComposeApp()
            }
        }
    }
}

// `@Composable` annotation to make Composable Function (a function to make layout)
@Composable
fun Greeting(name: String) {
    // make row layout
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        // display an image
        Image(
            painter =  painterResource(R.drawable.img_jetpack_compose),
            contentDescription = "Android Logo",
            modifier = Modifier.size(80.dp))
        // make column layout
        Column {
            // `Text` is a built-in function from Material library
            Text(
                text = "Hello $name!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold)
            Text(text = "Welcome to Machine.")
        }
    }
}

@Composable
fun GreetingList(names: List<String>) {
    if (names.isNotEmpty()) {
        Column {
            for (name in names) {
                Greeting(name = name)
            }
        }
    } else {
        Text("No people to greet :(")
    }
}

@Composable
fun HelloComposeApp() {
    // `Surface()` represents rectangular that serves as drawing canvas
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background) {
        GreetingList(names = sampleName)
    }
}

// `@Preview()` annotation to preview on 'Design' tab
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HelloComposeTheme {
        HelloComposeApp()
    }
}

// Tips: (CTRL+B) to show parameters of a function

// Jetpack Compose is a new UI framework on Kotlin with declarative approach
// Compose benefit:
// - less code
// - only use Kotlin (from logic, Gradle configuration, & layouting)
// - declarative, intuitive (provide Single Source of Truth (SSoT))
// - can make single independent component (good reusability & to test)
// --- can combine small element to single component (composition over inheritance)
// - separation of concern
// --- improve cohesion (linkages of function inside module) -> (uses 1 Kotlin file)
// --- less coupling (linkages of functions between modules) -> (XML & activity file)
// - XML interoperability (can use jetpack compose on XML, vice versa)
// - powerful API & tools (have direct access to Android platform API)

// examples of built-in Composable Function:
// - TextField(), Icon(), Image(), Button(), Checkbox(), RadioButton(), Switch(), etc

// best-practice in making composable's preview:
// - it needs to be made separated from used composable
// - preview's naming ends with "Preview" word
// - always use theme to display a preview
// - preview composable also works as documentation on how to use a component

// Jetpack Compose Tools:
// - Project Samples: can import samples of Jetpack Compose in `File -> New -> Import Sample`
// - Interactive Mode: can watch the interaction animation by clicking on 'Start interactive Mode' from icon on the right Preview's name (hand clicking icon)
// - Live Edit: can live edit using emulator
// - Animation Preview: can preview animation by clicking 'Animation Preview' from icon on the right on the Preview's name (motion icon)
// - Live Template: write composable component by writing shortcut:
// --- comp: make composable function @Composable
// --- prev: make composable function with @Preview
// --- W, WR, WC: add container Box, Row, or Column
// --- paddp: add padding modifier in dp
// --- weight: add weight modifier
// // Preview Parameter: preview multiple component based on multiple kind of  same-type parameter
/*
class UserPreviewParameterProvider: PreviewParameterProvider<User> {
    override val values = sequenceOf(
        User("Rickyslash")
        User("Miyamoto Musashi")
    )
}

fun DefaultPreview(
    @PreviewParameter(UserPreviewParameterProvider::class) user: User
    ) {
        MyComposeModifierTheme {
            contactCard(user.name)
        }
    }*/

// Declarative is a paradigm that describes the "what to do" intuitively
// Imperative is a paradigm that describes the "how" from a process that will be done (by writing the code 1 by 1)

// examples:
/* - Declarative:
fun main() {
    val number = listOf(1, 2, 3, 4, 5, 6, 7, 8)
    val oddNumber = mutableListOf<Int>()
    for (num in number) {
        if (num % 2 == 1) {
            oddNumber.add(num)
        }
    }
    print(oddNumber)
}*/
/* - Intuitive:
fun main() {
    val number = listOf(1, 2, 3, 4, 5, 6, 7, 8)
    val oddNumber = number.filter { it % 2 == 1 }
    print(oddNumber)
}*/