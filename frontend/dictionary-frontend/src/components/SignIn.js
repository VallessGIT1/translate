import {useState} from "react"
import {useAuth} from "../hooks/AuthProvider"
import EmailIcon from '@mui/icons-material/AlternateEmail'
import KeyIcon from '@mui/icons-material/Key'
import {
  Button, Input, Typography, FormControl, FormLabel, Box, Container, Stack
} from "@mui/joy"
import {useNavigate} from "react-router-dom";

const SignIn = () => {

  const auth = useAuth()
  const navigate = useNavigate()

  const [fields, setFields] = useState({username: "", password: ""})

  const handleSubmitEvent = event => {
    event.preventDefault()
    auth.signIn(fields)
  }

  const handleInput = event => {
    const {name, value} = event.target

    setFields((prev) => ({
      ...prev, [name]: value,
    }))
  }

  return (
      <Container component="main">
        <Box
            sx={{
              marginTop: 20,
              display: "flex",
              flexDirection: "column",
              alignItems: "center"
            }}
        >
          <Typography level="h3">Вход</Typography>
          <Box
              component="form"
              onSubmit={handleSubmitEvent}
              noValidate
              sx={{mt: 5}}
          >
            <FormControl sx={{mt: 2}}>
              <FormLabel>Имя пользователя</FormLabel>
              <Input
                  color="neutral"
                  size="md"
                  variant="soft"
                  name="username"
                  type="text"
                  startDecorator={<EmailIcon/>}
                  value={fields.username}
                  onChange={handleInput}
                  required
              />
            </FormControl>
            <FormControl sx={{mt: 2}}>
              <FormLabel>Пароль</FormLabel>
              <Input
                  color="neutral"
                  size="md"
                  variant="soft"
                  name="password"
                  type="password"
                  startDecorator={<KeyIcon/>}
                  value={fields.password}
                  onChange={handleInput}
                  required
              />
            </FormControl>
            <Stack direction="column">
              <Button
                  type="submit"
                  color="primary"
                  sx={{mt: 6}}
                  size="lg"
              >
                Войти
              </Button>
              <Button
                  variant="soft"
                  color="neutral"
                  sx={{mt: 2, mb: 9}}
                  size="sm"
                  onClick={() => navigate("/signUp")}
              >
                Зарегистрироваться
              </Button>
            </Stack>
          </Box>
        </Box>
      </Container>
  )
}

export default SignIn
