package com.fadlek.ai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Fadlek AI Brand Palette (Black + White + Electric Blue)
val FadlekBlack = Color(0xFF090C10)
val FadlekCard = Color(0xFF121822)
val FadlekBlue = Color(0xFF0284C7)
val FadlekCyan = Color(0xFF38BDF8)
val FadlekWhite = Color(0xFFFFFFFF)
val FadlekGray = Color(0xFF94A3B8)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FadlekApp()
        }
    }
}

@Composable
fun FadlekApp() {
    var isLoggedIn by remember { mutableStateOf(false) }
    var currentTab by remember { mutableStateOf(0) } // 0: AI Chat, 1: Real Estate Builder, 2: Plans

    if (!isLoggedIn) {
        AuthScreen(onLoginSuccess = { isLoggedIn = true })
    } else {
        Scaffold(
            containerColor = FadlekBlack,
            topBar = { TopBrandBar() },
            bottomBar = {
                NavigationBar(containerColor = FadlekCard, contentColor = FadlekWhite) {
                    NavigationBarItem(
                        selected = currentTab == 0,
                        onClick = { currentTab = 0 },
                        icon = { Icon(Icons.Default.AutoAwesome, "AI Chat") },
                        label = { Text("Fadlek AI") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = FadlekWhite,
                            indicatorColor = FadlekBlue,
                            selectedTextColor = FadlekCyan,
                            unselectedIconColor = FadlekGray,
                            unselectedTextColor = FadlekGray
                        )
                    )
                    NavigationBarItem(
                        selected = currentTab == 1,
                        onClick = { currentTab = 1 },
                        icon = { Icon(Icons.Default.HomeWork, "Real Estate") },
                        label = { Text("App Builder") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = FadlekWhite,
                            indicatorColor = FadlekBlue,
                            selectedTextColor = FadlekCyan,
                            unselectedIconColor = FadlekGray,
                            unselectedTextColor = FadlekGray
                        )
                    )
                    NavigationBarItem(
                        selected = currentTab == 2,
                        onClick = { currentTab = 2 },
                        icon = { Icon(Icons.Default.WorkspacePremium, "Plans") },
                        label = { Text("Plans") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = FadlekWhite,
                            indicatorColor = FadlekBlue,
                            selectedTextColor = FadlekCyan,
                            unselectedIconColor = FadlekGray,
                            unselectedTextColor = FadlekGray
                        )
                    )
                }
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding).fillMaxSize()) {
                when (currentTab) {
                    0 -> ChatStudioScreen()
                    1 -> RealEstateBuilderScreen()
                    2 -> SubscriptionPlansScreen()
                }
            }
        }
    }
}

@Composable
fun TopBrandBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(FadlekCard)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(FadlekBlue),
                contentAlignment = Alignment.Center
            ) {
                Text("F", color = FadlekWhite, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text("FADLEK AI", color = FadlekWhite, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
        Surface(
            color = FadlekBlue.copy(alpha = 0.2f),
            shape = CircleShape
        ) {
            Text(
                "ULTRA ENGINE",
                color = FadlekCyan,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
fun AuthScreen(onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FadlekBlack)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(FadlekBlue),
            contentAlignment = Alignment.Center
        ) {
            Text("F", color = FadlekWhite, fontSize = 40.sp, fontWeight = FontWeight.ExtraBold)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Fadlek AI", color = FadlekWhite, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Text(
            "Code • Search • Real Estate Apps • Media",
            color = FadlekCyan,
            fontSize = 13.sp
        )
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address", color = FadlekGray) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = FadlekBlue,
                unfocusedBorderColor = FadlekCard,
                focusedTextColor = FadlekWhite,
                unfocusedTextColor = FadlekWhite
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password", color = FadlekGray) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = FadlekBlue,
                unfocusedBorderColor = FadlekCard,
                focusedTextColor = FadlekWhite,
                unfocusedTextColor = FadlekWhite
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onLoginSuccess,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = FadlekBlue),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Login / Create Free Account", color = FadlekWhite, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ChatStudioScreen() {
    val modes = listOf("Fadlek-1 Pro (Code)", "Deep Search", "Image Gen", "Video Studio")
    var selectedMode by remember { mutableStateOf(modes[0]) }
    var prompt by remember { mutableStateOf("") }
    val messages = remember {
        mutableStateListOf(
            "Fadlek AI: Ready! Main coding, live web search, aur image/video generation ke liye taiyar hoon."
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(14.dp)) {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(modes) { mode ->
                val active = mode == selectedMode
                Surface(
                    modifier = Modifier.clickable { selectedMode = mode },
                    color = if (active) FadlekBlue else FadlekCard,
                    shape = CircleShape
                ) {
                    Text(
                        mode,
                        color = if (active) FadlekWhite else FadlekGray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(messages) { msg ->
                Surface(
                    color = if (msg.startsWith("You:")) FadlekBlue.copy(alpha = 0.25f) else FadlekCard,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        msg,
                        color = FadlekWhite,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(14.dp)
                    )
                }
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = prompt,
                onValueChange = { prompt = it },
                placeholder = { Text("Ask Fadlek AI anything...", color = FadlekGray) },
                modifier = Modifier.weight(1f),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = FadlekBlue,
                    unfocusedBorderColor = FadlekCard,
                    focusedTextColor = FadlekWhite,
                    unfocusedTextColor = FadlekWhite
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = {
                    if (prompt.isNotBlank()) {
                        messages.add("You: $prompt")
                        messages.add("Fadlek AI [$selectedMode]: Processing '$prompt' on high-speed cloud router...")
                        prompt = ""
                    }
                },
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(FadlekBlue)
            ) {
                Icon(Icons.Default.Send, "Send", tint = FadlekWhite)
            }
        }
    }
}

@Composable
fun RealEstateBuilderScreen() {
    var appName by remember { mutableStateOf("DreamHome Realty") }
    var city by remember { mutableStateOf("Mumbai / Delhi") }
    var generatedCode by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Real Estate App Generator", color = FadlekWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Generate full property listing app code & push to GitHub", color = FadlekGray, fontSize = 13.sp)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = appName,
            onValueChange = { appName = it },
            label = { Text("Real Estate App Name", color = FadlekGray) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(focusedTextColor = FadlekWhite, unfocusedTextColor = FadlekWhite)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Target City / Region", color = FadlekGray) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(focusedTextColor = FadlekWhite, unfocusedTextColor = FadlekWhite)
        )
        Spacer(modifier = Modifier.height(14.dp))

        Button(
            onClick = {
                generatedCode = """
// Generated Real Estate App: $appName ($city)
// Features: Buy/Rent Filter, Property Map, Direct Agent WhatsApp, Admin Panel
data class Property(
    val id: String,
    val title: String,
    val price: String,
    val location: String = "$city",
    val bhk: Int
)
// Ready to push to GitHub Repository!
                """.trimIndent()
            },
            modifier = Modifier.fillMaxWidth().height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = FadlekBlue)
        ) {
            Text("Generate Real Estate App Code", color = FadlekWhite, fontWeight = FontWeight.Bold)
        }

        if (generatedCode.isNotEmpty()) {
            Spacer(modifier = Modifier.height(14.dp))
            Surface(
                color = FadlekCard,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth().border(1.dp, FadlekBlue, RoundedCornerShape(12.dp))
            ) {
                Text(generatedCode, color = FadlekCyan, fontSize = 12.sp, modifier = Modifier.padding(14.dp))
            }
        }
    }
}

@Composable
fun SubscriptionPlansScreen() {
    val plans = listOf(
        Triple("1. Starter (Free)", "$0 / month", "Daily AI Chats, Basic Real Estate Code, Standard Speed"),
        Triple("2. Lite Builder", "$2.99 / month", "Unlimited Coding, GitHub Repo Export, 50 HD Images"),
        Triple("3. Fadlek Pro", "$7.99 / month", "Fadlek-1 Ultra Model, Deep Web Search, AI Video Gen"),
        Triple("4. Agency Ultra", "$14.99 / month", "Full Real Estate White-Label Apps, Priority Millisecond API")
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("Affordable AI Plans", color = FadlekWhite, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text("Sabse sasta aur powerful all-in-one AI subscription", color = FadlekGray, fontSize = 13.sp)
        }
        items(plans) { (title, price, desc) ->
            Surface(
                color = FadlekCard,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth().border(1.dp, FadlekBlue.copy(alpha = 0.5f), RoundedCornerShape(14.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(title, color = FadlekWhite, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                        Text(price, color = FadlekCyan, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(desc, color = FadlekGray, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(containerColor = FadlekBlue),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Select Plan", color = FadlekWhite)
                    }
                }
            }
        }
    }
}
