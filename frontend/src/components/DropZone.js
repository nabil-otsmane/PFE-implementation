import React, { useCallback } from 'react'
import {useDropzone} from 'react-dropzone'
import { toast } from 'react-toastify'
import './DropZone.css'

export default function DropZone({ add }) {

    const onDrop = useCallback(acceptedFiles => {
        acceptedFiles.forEach((file) => {
            const reader = new FileReader()
      
            reader.onabort = () => console.log('file reading was aborted')
            reader.onerror = () => console.log('file reading has failed')
            reader.onload = () => {
            // Do whatever you want with the file contents
                const binaryStr = reader.result
                try {
                    const str = String.fromCharCode.apply(null, new Uint8Array(binaryStr))
    
                    add({
                        name: file.name,
                        resources: JSON.parse(str)
                    })

                } catch(e) {
                    toast('Format incorrecte des donnees', { type: 'error' })
                }
            }
            reader.readAsArrayBuffer(file)
        })
    }, [add])
    
    const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

    return (
        <div className='dropzone-container'>
            <div {...getRootProps()}>
                <div className='image-container'>
                    <img src="/images/drop-down.png" alt="upload here" />
                </div>
                <div className='text-container'>
                    <p>
                        {isDragActive? "Upload your files or drag them here": "Drop your files here"}
                    </p>
                </div>
            </div>
            <input type="file" style={{ display: 'none' }} {...getInputProps()} />
        </div>
    )
}
