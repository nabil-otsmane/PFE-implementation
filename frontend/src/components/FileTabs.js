import React from 'react'

function Tab({ id, name, active, setActive }) {

    return <div className={'file-tab' + (active? " selected": '')} onClick={() => setActive(id)}>
        <img src={!active ? "/images/file_black.png": "/images/file_white.png"} alt="file" />
        <span>{name}</span>
    </div>
}

export default function FileTabs({ tabs, selected, setSelected }) {

    return (
        <span className='tabsContainer'>
            {tabs.map(e => <Tab id={e.id} key={e.id} name={e.name} active={selected === e.id} setActive={setSelected} />)}
        </span>
    )
}
