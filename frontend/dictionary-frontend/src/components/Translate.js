import React, {useEffect, useState} from "react"
import {
  Box, Typography, Input, Button, Select, Option,
  FormControl, FormLabel, CircularProgress, Textarea, Divider
} from "@mui/joy"
import toast from "react-hot-toast"
import {BASE_API_URL} from "../Constants"
import {useAuth} from "../hooks/AuthProvider"
import TranslateIcon from '@mui/icons-material/Translate'
import TextPrview from "./TextPrview";

const Translate = () => {

  const auth = useAuth()

  const [languages, setLanguages] = useState([]);

  const [text, setText] = useState("");
  const [translated, setTranslated] = useState("");
  const [source, setSource] = useState(null);
  const [target, setTarget] = useState(null);
  const [transcription, setTranscription] = useState("");
  const [description, setDescription] = useState("");

  const [loading, setLoading] = useState(false);

  useEffect(() => {
    fetchLanguages()
  }, []);

  const fetchLanguages = async () => {
    try {
      const response = await fetch(`${BASE_API_URL}language/list`, {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Basic ${auth.token}`,
        },
      })

      if (response.status === 401) {
        throw new Error("Необходимо авторизоваться")
      }
      if (response.status === 403) {
        throw new Error("Недостаточно прав для выполнения операции")
      }

      const json = await response.json()

      if (response.status === 200) {
        setLanguages(json)
      } else {
        throw new Error(json.message)
      }
    } catch (error) {
      toast.error(error.message)
      console.error(error)
    }
  };

  const translateHandler = async () => {
    try {

      if (text.trim() === "") {
        setTranslated("")
        throw new Error("Введите текст для перевода")
      }

      if (!target) {
        throw new Error("Выберите язык перевода")
      }

      setLoading(true);
      setTranslated("");
      setTranscription("");
      setDescription("");

      const response = await fetch(`${BASE_API_URL}translation`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Basic ${auth.token}`,
        },
        body: JSON.stringify({
          text: text,
          source: source,
          target: target,
        }),
      });

      if (response.status === 401) {
        throw new Error("Необходимо авторизоваться");
      }
      if (response.status === 403) {
        throw new Error("Недостаточно прав для выполнения операции");
      }

      const json = await response.json();

      if (response.status === 200) {
        setTranslated(json.translation);
        setSource(json.source);
        setTranscription(json.transcription || "Нет транскрипции");
        setDescription(json.description || "Нет описания");
      } else {
        throw new Error(json.message);
      }

    } catch (error) {
      toast.error(error.message);
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  return (
      <Box
          sx={{
            display: "flex",
            flexDirection: {md: "column"},
            alignItems: "center",
          }}
      >
        <Box
            sx={{
              display: "flex",
              flexDirection: {xs: "column", md: "row"},
              p: 3,
              width: "100%",
              maxWidth: 800,
              mx: "auto",
              mt: 10,
            }}
        >
          <Box
              sx={{
                display: "flex",
                flexDirection: "column",
                width: {xs: "100%", md: "50%"},
                mr: {md: 2},
              }}
          >
            <Typography level="h6" component="h2" sx={{mb: 3}}>Оригинал</Typography>
            <FormControl sx={{mb: 2, width: "100%"}}>
              <FormLabel>Язык оригинала</FormLabel>
              <Select
                  variant="soft"
                  value={source}
                  onChange={(_, value) => setSource(value)}
                  sx={{width: "200px"}}
              >
                <Option value={null}>Определить</Option>
                {languages.map(language => <Option value={language.code}>{language.display}</Option>)}
              </Select>
            </FormControl>
            <FormControl sx={{mb: 2, width: "100%"}}>
              <FormLabel>Введите слово</FormLabel>
              <Textarea
                  variant="soft"
                  minRows={2}
                  maxRows={2}
                  value={text}
                  onChange={(e) => setText(e?.target.value)}
                  required
              />
            </FormControl>
          </Box>
          <Divider orientation="vertical"/>
          <Box
              sx={{
                display: "flex",
                flexDirection: "column",
                width: {xs: "100%", md: "50%"},
                ml: {md: 2},
              }}
          >
            <Typography level="h6" component="h2" sx={{mb: 3}}>Перевод</Typography>
            <FormControl sx={{mb: 2, width: "100%"}}>
              <FormLabel>Язык перевода</FormLabel>
              <Select
                  variant="soft"
                  placeholder="Выберите язык"
                  value={target}
                  onChange={(_, value) => setTarget(value)}
                  sx={{width: "200px"}}
              >
                {languages.map(language => <Option value={language.code}>{language.display}</Option>)}
              </Select>
            </FormControl>
            <FormControl sx={{mb: 2, width: "100%"}}>
              <FormLabel>Перевод</FormLabel>
              <TextPrview>{translated}</TextPrview>
            </FormControl>
            <FormControl sx={{mb: 2, width: "100%"}}>
              <FormLabel>Транскрипция</FormLabel>
              <TextPrview>{transcription}</TextPrview>
            </FormControl>
            <FormControl sx={{mb: 2, width: "100%"}}>
              <FormLabel>Описание</FormLabel>
              <TextPrview>{description}</TextPrview>
            </FormControl>
          </Box>
        </Box>
        <Button
            color="primary"
            size="lg"
            sx={{width: "fit-content"}}
            onClick={translateHandler}
            endDecorator={loading && <CircularProgress/>}
            startDecorator={<TranslateIcon/>}
        >
          Перевести
        </Button>
      </Box>
  );
};

export default Translate;