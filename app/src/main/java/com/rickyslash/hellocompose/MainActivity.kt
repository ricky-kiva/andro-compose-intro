package com.rickyslash.hellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rickyslash.hellocompose.ui.theme.HelloComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // this to deploy composable function on Activity
        setContent {
            // this to set the 'theme' of the Compose
            HelloComposeTheme {
                // `Surface()` represents rectangular that serves as drawing canvas
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    // call the custom `Greeting()` Composable Function
                    Greeting("Rickyslash")
                }
            }
        }
    }
}

// `@Composable` annotation to make Composable Function (a function to make layout)
@Composable
fun Greeting(name: String) {
    // `Text` is a built-in function from Material library
    Text(text = "Hello $name!")
}

// `@Preview()` annotation to preview on 'Design' tab
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HelloComposeTheme {
        Greeting("World")
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