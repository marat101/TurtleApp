package com.turtleteam.ui.screens.screen_notifications

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import ru.turtleteam.theme.R
import ru.turtleteam.theme.LocalColors
import ru.turtleteam.theme.LocalShapes
import org.koin.androidx.compose.getViewModel
import java.util.Date
import java.util.Locale


@Composable
fun NotificationsScreen(
    viewModel: NotificationsViewModel = getViewModel()
) {

    val state = viewModel.state.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            reverseLayout = true,
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Bottom)
        ) {
            items(state.value.notifications) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(4.dp, LocalShapes.current.medium)
                        .background(
                            LocalColors.current.baseItemBackground,
                            LocalShapes.current.medium
                        )
                        .padding(5.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(text = it.title, color = LocalColors.current.numberBackground)
                    Text(text = it.description, color = LocalColors.current.numberBackground)
                    Text(
                        text = it.sentTime.toDate(),
                        color = Color.Gray
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(LocalColors.current.baseItemBackground)
                .clickable(MutableInteractionSource(), indication = rememberRipple(), onClick = {
                    val clipboard =
                        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    clipboard.setPrimaryClip(
                        ClipData.newPlainText(
                            "Token",
                            "Device-id: `${state.value.deviceId}`\n\nToken: `${state.value.fcmToken}`"
                        )
                    )
                    Toast
                        .makeText(context, "Скопировано", Toast.LENGTH_SHORT)
                        .show()
                })
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1F),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(text = buildAnnotatedString {
                    withStyle(SpanStyle(color = LocalColors.current.textColor)) {
                        append("Device_id: ")
                    }
                    withStyle(SpanStyle(color = Color.Gray)) {
                        append(state.value.deviceId)
                    }
                })
                Text(text = buildAnnotatedString {
                    withStyle(SpanStyle(color = LocalColors.current.textColor)) {
                        append("Token: ")
                    }
                    withStyle(SpanStyle(color = Color.Gray)) {
                        append(state.value.fcmToken)
                    }
                }, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }

            Icon(
                modifier = Modifier.padding(horizontal = 10.dp),
                painter = painterResource(id = R.drawable.ic_copy),
                contentDescription = "",
                tint = LocalColors.current.numberBackground
            )
        }
    }
    DisposableEffect(key1 = Unit, effect = {

//        when (Build.BRAND.uppercase()) {
//            "REALME" -> {
//                try {
//                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//                    intent.data = Uri.parse("package:" + context.packageName)
//                    context.startActivity(intent)
//                } catch (e: ActivityNotFoundException) {
//                    val intent = Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS)
//                    context.startActivity(intent)
//                }
//            }
//            else -> {
//                val autoStart = AutoStartPermissionHelper.getInstance()
//                if (!autoStart.isAutoStartPermissionAvailable(context)) {
//                    autoStart.getAutoStartPermission(context)
//                }
//            }
//        }
        onDispose {}
    })
}

//class Brands(private val _context: Context, brand: String) {
//    /**
//     * Request AutoStart permission based on [Brand] type.
//     * Note-> No permission required for [Brand.OTHER].
//     */
//    fun requestAutoStartPermission() {
//        when (Build.BRAND.uppercase().toEnum(Brand::name, Brand.OTHER)) {
//            Brand.OPPO -> oppoAutoStart()
//            Brand.OTHER -> {}
//        }
//    }
//
//    private fun startAutoStartActivity(context: Context, packageName: String, componentName: String) {
//        val intentAutoStartPage = Intent().apply {
//            component = ComponentName(packageName, componentName)
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        }
//        try {
//            context.startActivity(intentAutoStartPage)
//        } catch (e: Exception) {
//        }
//    }
//
//    /**
//     * Request AutoStart permission for [Brand.OPPO].
//     */
//    private fun oppoAutoStart() {
//        if (isPackageExists(OPPO_MAIN) || isPackageExists(OPPO_FALLBACK)) {
//            try {
//                startAutoStartActivity(_context, OPPO_MAIN, OPPO_COMPONENT)
//
//            } catch (e: Exception) {
//
//                try {
//                    startAutoStartActivity(_context, OPPO_FALLBACK, OPPO_COMPONENT_FALLBACK)
//
//                } catch (ex: Exception) {
//
//                    try {
//                        startAutoStartActivity(_context, OPPO_MAIN, OPPO_COMPONENT_FALLBACK_A)
//
//                    } catch (exx: Ex) {
//                    }
//                }
//            }
//        }
//    }
//
//    private fun isPackageExists(targetPackage: String): Boolean {
//        val packages = _context.packageManager.getInstalledApplications(0)
//        for (packageInfo in packages) {
//            if (packageInfo.packageName.equals(targetPackage)) return true
//        }
//
//        return false
//    }
//
//    private enum class Brand {
//        OPPO,
//        VIVO,
//        OTHER
//    }
//
//    private companion object BrandPackage {
//
//        // Oppo
//        private val OPPO_MAIN = "com.coloros.safecenter"
//        private val OPPO_FALLBACK = "com.oppo.safe"
//        private val OPPO_COMPONENT = "com.coloros.safecenter.permission.startup.StartupAppListActivity"
//        private val OPPO_COMPONENT_FALLBACK = "com.oppo.safe.permission.startup.StartupAppListActivity"
//        private val OPPO_COMPONENT_FALLBACK_A = "com.coloros.safecenter.startupapp.StartupAppListActivity"
//    }
//}

fun Long.toDate(): String = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ROOT).format(Date(this))