import React, { useState } from 'react'
import './FileList.css'

function Bar({ leftSide = null, rightSide }) {
  return (
    <div className='bar'>
      <div className='left'>
        {leftSide}
      </div>
      <div className='right'>
        {rightSide}
      </div>
    </div>
  )
}

function Input({ id, clicked, setClicked }) {
  
  return <div className='input-circle' onClick={() => setClicked(id)}>
    {clicked && <div></div>}
  </div>
}

function InputContainer({ setMethod }) {
  const [clicked, setClicked] = useState(-1)

  function set(id) {
    setClicked(id)
    setMethod(id)
  }

  return (<div className='input-container'>
    <Input id={1} clicked={clicked === 1} setClicked={set} />
    <Input id={2} clicked={clicked === 2} setClicked={set} />
    <Input id={3} clicked={clicked === 3} setClicked={set} />
  </div>)
}


export default function FileList({ files, generate, setFiles }) {

  function setMethod(id) {
    return method => {
      setFiles(prev => {
        const n = [...prev]

        n[id].method = method

        return n
      })
    }
  }

  return (
    <div className='container'>
      <Bar rightSide={<>
        <span className='side-text'>Paralléle</span>
        <span className='side-text'>Différée</span>
        <span className='side-text'>Les deux</span>
      </>} />
      <div className='files-container'>
        <div className='scrollable'>
          {files.map((e, i) => <Bar key={i} leftSide={<div className='left-container'><img src="/images/file.png" alt="file" /> <p>{e.name}</p></div>} rightSide={<InputContainer setMethod={setMethod(i)} />} />)} 
        </div>
      </div>
      <div className='generate-container'>
        <button onClick={generate}>Visualiser ressources</button>
      </div>
    </div>
  )
}
