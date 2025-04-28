import React from "react";
import {useAuth} from "../hooks/AuthProvider";
import {
  Box,
  Typography,
  Button,
  IconButton,
} from "@mui/joy";
import MenuIcon from "@mui/icons-material/Menu";
import LoginIcon from '@mui/icons-material/Login';
import {useNavigate} from "react-router-dom";

const Header = () => {

  const auth = useAuth()
  const navigate = useNavigate()

  const handleLogout = () => {
    auth.signOut();
  };

  return (
      <Box
          sx={{
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            bgcolor: "background.surface",
            color: "text.primary",
            py: 1.5,
            px: 3,
            boxShadow: 2,
          }}
      >
        <Box sx={{display: "flex", alignItems: "center"}}>
          <IconButton
              size="sm"
              color="neutral"
              aria-label="menu"
              sx={{mr: 2}}
          >
            <MenuIcon/>
          </IconButton>
          <Typography variant="h6" sx={{fontWeight: "lg", cursor: "pointer", mr: 2}} onClick={() => navigate("/")}>
            Главная
          </Typography>
          <Typography variant="h6" sx={{fontWeight: "lg", cursor: "pointer", mr: 2}} onClick={() => navigate("/translate")}>
            Переводчик
          </Typography>
          <Typography variant="h6" sx={{fontWeight: "lg", cursor: "pointer", mr: 2}} onClick={() => navigate("/dictionary")}>
            Словарь
          </Typography>
        </Box>
        <Box sx={{display: "flex", alignItems: "center"}}>
          {auth.loggedIn() ? (
              <>
                <Button
                    color="neutral"
                    variant="soft"
                    onClick={handleLogout}
                    sx={{mr: 1}}
                >
                  Выйти
                </Button>
              </>
          ) : (
              <>
                <Button
                    color="neutral"
                    variant="plain"
                    onClick={() => navigate("/signIn")}
                    sx={{mr: 1}}
                >
                  <LoginIcon/>
                </Button>
              </>
          )}
        </Box>
      </Box>
  );
};

export default Header;