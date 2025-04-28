const style = {
  padding: 10,
  borderRadius: 5,
  width: 300,
  backgroundColor: "#f0f4f8",
  minHeight: 20,
  color: "grey"
}

const TextPreview = ({children}) => {
  return (
      <div style={style}>
        {children}
      </div>
  )
}

export default TextPreview