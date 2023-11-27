package com.anne.play.ui.page.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.anne.play.R
import com.anne.play.logic.model.LoginModel
import com.anne.play.logic.model.PlayState
import com.anne.play.ui.main.PlayActions
import com.anne.play.ui.view.PlayAppBar
import com.anne.play.ui.view.edit.*
import kotlinx.coroutines.launch

/**
 *
 * Author:AnneLo
 * Time:2023/10/4
 */
sealed class SignInEvent {
    data class SignIn(val email: String, val password: String) : SignInEvent()
    object SignUp : SignInEvent()
    object SignInAsGuest : SignInEvent()
    object NavigateBack : SignInEvent()
}

@Composable
fun LoginPage(
    onNavigationEvent: PlayActions,
    loginState: PlayState<LoginModel>?,
    toLogout: () -> Unit,
    toLoginOrRegister: (Account) -> Unit,
) {
    SignIn(onNavigationEvent = { event ->
        when (event) {
            is SignInEvent.SignIn -> {
                toLoginOrRegister(Account(event.email, event.password, true))
            }

            SignInEvent.SignUp -> {
                toLogout()
            }

            SignInEvent.SignInAsGuest -> {
                toLogout()
            }

            SignInEvent.NavigateBack -> {
                onNavigationEvent.upPress()
            }
        }
    })
    when (loginState) {
        is PlayState.PlayLoading -> {
        }

        is PlayState.PlaySuccess -> {
            onNavigationEvent.upPress()
        }

        is PlayState.PlayError -> {
        }

        else -> {}
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignIn(onNavigationEvent: (SignInEvent) -> Unit) {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val snackBarErrorText = stringResource(id = R.string.feature_not_available)
    val snackBarActionLabel = stringResource(id = R.string.close)

    Scaffold(
        topBar = {
            PlayAppBar(
                title = stringResource(id = R.string.sign_in),
                click = { onNavigationEvent(SignInEvent.NavigateBack) },
            )
        },
        content = {
            SignInSignUpScreen(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    SignInContent(
                        onSingInSubmitted = { email, password ->
                            onNavigationEvent(SignInEvent.SignIn(email, password))
                        },
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextButton(
                        onClick = {
                            scope.launch {
                                snackBarHostState.showSnackbar(
                                    message = snackBarErrorText,
                                    actionLabel = snackBarActionLabel,
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(text = stringResource(id = R.string.forgot_password))
                    }
                }
            }
        },
    )
    Box(modifier = Modifier.fillMaxSize()) {
        ErrorSnackBar(
            snackBarHostState = snackBarHostState,
            onDismiss = { snackBarHostState.currentSnackbarData?.dismiss() },
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}

@Composable
fun ErrorSnackBar(
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = { },
) {
    SnackbarHost(
        hostState = snackBarHostState,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier.padding(16.dp),
                content = {
                    Text(
                        text = data.message,
                        style = MaterialTheme.typography.body2,
                    )
                },
                action = {
                    data.actionLabel?.let {
                        TextButton(onClick = onDismiss) {
                            Text(
                                text = stringResource(id = R.string.dismiss),
                                color = MaterialTheme.colors.error,
                            )
                        }
                    }
                },
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Bottom),
    )
}

@Composable
fun SignInContent(
    onSingInSubmitted: (email: String, password: String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val focusRequester = remember { FocusRequester() }
        val emailState = remember { EmailState() }
        Email(emailState, onImeAction = { focusRequester.requestFocus() })

        Spacer(modifier = Modifier.height(16.dp))

        val passwordState = remember { PasswordState() }
        Password(
            label = stringResource(id = R.string.password),
            passwordState = passwordState,
            modifier = Modifier.focusRequester(focusRequester),
            onImeAction = { onSingInSubmitted(emailState.text, passwordState.text) },
        )
        Button(
            onClick = { onSingInSubmitted(emailState.text, passwordState.text) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            enabled = emailState.isValid && passwordState.isValid,
        ) {
            Text(text = stringResource(id = R.string.sign_in))
        }
    }
}
