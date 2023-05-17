package com.rickyslash.hellocompose

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rickyslash.hellocompose.ui.theme.HelloComposeTheme
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Devices

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
        setContent { // this to deploy composable function on Activity
            HelloComposeTheme { // this to set the 'theme' of the Compose
                HelloComposeApp() // call the custom Composable Function that has been made
            }
        }
    }
}

// `@Composable` annotation to make Composable Function (a function to make layout)
@Composable
fun Greeting(name: String) {
    var isExpanded by remember { mutableStateOf(false) } // add state variable (by if you import setValue & getValue)
    val animatedSizeDp by animateDpAsState( // add spring animation by dp
        targetValue = if (isExpanded) 100.dp else 80.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Card( // add card display
        backgroundColor = MaterialTheme.colors.primary,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {

        Row( // make row layout
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Image( // display an image
                painter = painterResource(R.drawable.img_jetpack_compose),
                contentDescription = "Android Logo",
                modifier = Modifier.size(animatedSizeDp)
            )
            Column(modifier = Modifier.weight(1f)) { // make column layout
                Text( // `Text` is a built-in function of Compose
                    text = "Hello $name!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Welcome to Machine.",
                    style = MaterialTheme.typography.body1.copy( // "copy" is used to modify typography theme as needed
                        fontStyle = FontStyle.Italic)
                )
            }
            IconButton(onClick = { isExpanded = !isExpanded }) { // set icon button
                Icon(
                    imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Outlined.ExpandMore,
                    contentDescription = if (isExpanded) "Show less" else "Show more"
                )
            }
        }
    }
}

@Composable
fun GreetingList(names: List<String>) {
    if (names.isNotEmpty()) {
        LazyColumn {
            items(names) { name ->
                Greeting(name)
            }
        }
    } else {
        Text("No people to greet :(")
    }
}

@Composable
fun HelloComposeApp() {
    Surface( // `Surface()` represents rectangular that serves as drawing canvas
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background) {
        GreetingList(names = sampleName)
    }
}

// `@Preview()` annotation to preview on 'Design' tab
// @Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = UI_MODE_NIGHT_YES) // this if want to display in night mode
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

// 2 main component of Compose (Tech Stack):
// - Development Host:
// --- Android Studio: have tool to build compose
// --- Compose Compiler Plugin: to generate code tagged @Composable to UI
// --- Kotlin Compiler: use features of Kotlin
// - Device:
// --- Compose Runtime: the core of Compose that act to add things to structure tree
// --- Compose UI Core: to measure, layout, and draw UI
// --- Compose UI Foundation: includes basic building block like Text, Image, Row, Column, etc
// --- Compose UI Material: implementation of Material Design to Compose like Button, Card, FAB, etc

// Kotlin Features in Compose:
// - Named Argument & Default Argument:
// --- default:
/* Text("Click Me") */
// --- named:
/* Text(
     text = number.toString(),
     modifier = Modifier.align(Alignment.CenterHorizontally),
     style = MaterialTheme.typography.h2,
     fontStyle = FontStyle.Italic,
)*/

// - Scope & Receiver:
/* Column {
    Text(
        // can access `Alignment.CenterHorizontally` because it's inside `Column{}`
        // can't access `Alignment.CenterVertically` because it only could be accessed in `Row{}`
        modifier = Modifier.align(Alignment.CenterHorizontally),
)}*/

// - Singleton Object: could make singleton object, use keyword "object"
// --- example of usage is in calling property from material design like typography, color, or shapes:
// --- `style = MaterialTheme.typography.h2,`

// - Trailing comma: enable to keep comma on the last parameter
/* Text(
        text = number.toString(),
        modifier = Modifier.align(Alignment.CenterHorizontally),
        style = MaterialTheme.typography.h2,
        fontStyle = FontStyle.Italic, //trailing comma
)*/

// - DSL (Domain Specific Language): write code based on domain of specific app safely
/* LazyRow { // DSL
        item { // add single item
              Text("List of Numbers:")
        }
        items(number){ // add list item
             Text("${it+1}")
        }
}*/

// - Higher-order Function (HoF) & Lambda Expression:
// HoF is a function that uses another function as parameter, or use it as return type
/* example:
    MyButton { number += 1 }
    …
    fun MyButton(onButtonClicked: () -> Unit) { //higher-order function
        Button(onClick = onButtonClicked) { //lambda expression
            Text("Click Me")
        }
}*/

// - Delegated Properties: is a property that the value is delegated to another place
/* example:
    var number by remember {
         mutableStateOf(0)
    }*/

// - Top Level Function: we can use top-level function (defined at outermost level of a module) and utilize it to any Compose function

// `@Composable` act as an annotation for Jetpack Compose to work
/* The way of using `@Composable` is the same as using suspend
    // function declaration
    @Composable fun MyFun() { … }

    // lambda declaration
    val myLambda = @Composable { … }

    // function type
    fun MyFun(myParam: @Composable () -> Unit) { … }
*/
/* Composable function only compatible if nested in Composable function
    fun Example(a: () -> Unit, b: @Composable () -> Unit) {
       a() // allowed
       b() // NOT allowed
    }

    @Composable
    fun Example(a: () -> Unit, b: @Composable () -> Unit) {
       a() // allowed
       b() // allowed
    }
*/

// Execution Model: every @Composable function will be translated by compiler by injecting parameter `$composer` as a calling context
// - parameter `%composer` will be continued by other Composable Function inside @Composable
// - `$composer` will call `start` in the beginning & put an `object group` into 'slot table' with certain id, then call `end` in the end
// --- 'slot table' use 'Gap Buffer' data structure
/* example of compiled Composable function:
fun NamePlate(name: String, lastname: String, $composer: Composer<*>) {
@Composable
fun CounterApp($composer: Composer) {
   $composer.start(123)
    Column {
        var number by remember($composer) { // key 456
            mutableStateOf(0)
        }
        Button(
              $composer, // key 789
              onClick = { number += 1 }
        ) {
              Text("Count: ${number.value}")
        }
    }
    $composer.end()
}*/

// the form of 'slot table' will be:
// Counter (Group(123) | remember (Group(456) | State(0)) | Button (Ground(789) | "Count: 0" | {...} | Button(...)))

// 'slot table' could be changed based on condition:
/* example function:
@Composable fun App() {
    val result = getData()
    if (result == null) {
        Loading(...)
    } else {
        Header(result)
        Body(result)
    }
}*/
// - null -> App (Group(123) | Loading(...))
// - not null -> App (Group(456) | Header | Body))

// Positional Memoization: Composable function can save output based on its position on 'slot table'
// - Function Memoization: ability for function to save output (with key as marker) into cache according to the input

// Remember: is a wrapper to to save variable that is a non-Composable Function into cache. It's lightweight because it only store location
/* example:
@Composable
fun App(items: List<String>, query: String) {
    val results = remember {
        items.filter { it.matches(query) }
    }
    ...
}*/

// Storing Parameter: Compose will save every parameter of Composable Function into 'slot table'
/* example:
@Composable fun Dicoding(number: Int) {
    Address(
        number = number,
        street = "Batik Kumeli",
        city = "Bandung",
        country = "Indonesia",
        zip = "50123",
    )
}

@Composable fun Address(
    number: Int,
    street: String,
    city: String,
    country: String,
    zip: String,
) {
    Text(“Alamat:)
    Text("$number $street")
    Text(city)
    Text(", ")
    Text(country)
    Text(" ")
    Text(zip)
}*/

/* What's being done on compiler:
fun Dicoding(
    $composer: Composer,
    $static: Int,
    number: Int
) {
    if (number == $composer.next()) {
        Address(
            $composer,
            number=number,
            street="Batik Kumeli",
            city="Bandung",
    ) else {
        $composer.skip()
    }
}

fun Address(
    $composer: Composer,
    $static: Int,
    number: Int,
    street: String,
    city: String,
    country="Indonesia",
    zip="50123",
) {
    Text($composer, 0b1, “Alamat:”)
    Text($composer, ($static and 0b11) and (($static and 0b10) shr 1), "$number $street")
    Text($composer, ($static and 0b100) shr 2, city)
    Text($composer, 0b1, ", ")
    Text($composer, ($static and 0b1000) shr 3, country)
    Text($composer, 0b1, " ")
    Text($composer, ($static and 0b10000) shr 4, zip)

}
*/

// UI update process with Jetpack Compose:
// - make composable function that is immutable
// - when the function is being run, the first UI being made is called 'Initial Composition'
// - when there is UI change (input, state change, etc) Composable Function will be called again
// --- it will renew 'slot table' & build a brand new UI

// Recomposition: the condition of Composable Function on making a brand new UI

// best practice on Recomposition:
// - Fast: ensure the process is fast. If the process is heavy (read database or accessing API), do it on background thread & send to parameter
// - Idempotent: calling Composable Function with same input will always have the same output
// - Side-effect-free: side effect is an app status change because of factor outside of the Composable Function. Use Effect API to control it

// Consideration in Recomposition:
// - Composable Function could be run with different order:
// --- system will search for highest priority element and execute it first
// --- every Composable Function need to be independent

// - Composable Function could be run together (in Parallel)
// --- this makes us need to ensure that there is no side-effect that possibly happen in the Composable Function
/* `items` variable value could change when recomposition is happening:
@Composable
@Deprecated("Example with bug")
fun ListWithBug(myList: List<String>) {
    var items = 0

    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            for (item in myList) {
                Text("Item: $item")
                items++ // Avoid! Side-effect of the column recomposing.
            }
        }
        Text("Count: $items")
    }
}*/

// - Recomposition will skip unchanged UI
/* example:
@Composable
fun NameList(
    header: String,
    names: List<String>,
) {
    Column {
        // this will recompose when [header] changes, but not when [names] changes
        Text(header, style = MaterialTheme.typography.h5)
        Divider()
        LazyColumn {
            items(names) { name ->
                // the adapter will recompose when item's [name] updated, but not when [header] changes
                Text(name)
            }
        }
    }
}*/

// - Recomposition is reliable
// --- if parameter changed when recomposition hasn't been finished, Compose will abort Recomposition & reset with new parameter

// - Composable Function could be called multiple times
// --- example: when making animation
// --- in handling with heavy process, better to do it outside Composable Function and send it using parameter
