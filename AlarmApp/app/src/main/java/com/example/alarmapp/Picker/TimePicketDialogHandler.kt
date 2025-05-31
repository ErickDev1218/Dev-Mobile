import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.util.*

@Composable
fun TimePickerDialogHandler(
    show: Boolean,
    onTimeSelected: (Int, Int) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    if (show) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(
            context,
            { _, selectedHour: Int, selectedMinute: Int ->
                onTimeSelected(selectedHour, selectedMinute)
            },
            hour,
            minute,
            true
        ).apply {
            setOnDismissListener {
                onDismiss()
            }
            show()
        }
    }
}
