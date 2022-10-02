import React, { useMemo } from 'react'
import ReactApexChart from 'react-apexcharts'

function format(resources) {
  return resources.map(({ id, name, beginTime, endTime, fillColor }) => ({ x: name, y: [beginTime, endTime], fillColor }))
}

function formatOverlaps(overlap) {
  if (overlap === undefined || overlap.length === 0)
    return [];
  
  if (overlap.length > 0) {

    const result = overlap.reduce((a, b) => [...a, ...b.resources.map(({name}) => ({x: name, y: [b.beginTime, b.endTime], fillColor: "#E91E63"}))], [])

    
    return result
  }

  return overlap.resources.map(({name}) => ({x: name, y: [overlap.beginTime, overlap.endTime], fillColor: "#E91E63"}))
}

const options = {
  chart: {
    height: 200,
    type: 'rangeBar',
    zoom: {
      enabled: true,
      type: 'xy',  
      autoScaleYaxis: true,  
    }
  },
  plotOptions: {
    bar: {
      horizontal: true,
      distributed: true,
      dataLabels: {
        hideOverflowingLabels: false
      }
    }
  },
  colors: ['#2E93fA', '#66DA26', '#546E7A', '#FF9800'],
  dataLabels: {
    enabled: true,
    formatter: function(val, opts) {
      var label = opts.w.globals.labels[opts.dataPointIndex]
      
      return label;
    },
    style: {
      colors: ['#f3f4f5', '#fff'],
      fontSize: '20px',
    }
  },
  xaxis: {
    type: 'number'
  },
  yaxis: {
    show: true
  },
  grid: {
    row: {
      colors: ['#EAFFE5', '#EAFFEE'],
      opacity: 1
    }
  }
}

export default function FileGraph({ resources, overlap }) {

  const formattedRessource = useMemo(() => format(resources), [resources]);

  const overlapResources = useMemo(() => formatOverlaps(overlap), [overlap])

  return (
    <div className='fileGraph'>
      <ReactApexChart options={options} type="rangeBar" series={[{name: "series 1", data: [...formattedRessource, ...overlapResources]}]} height="95%" width="95%" /> 
    </div>
  )
}
