import * as React from 'react'
import { compose, withState, branch, lifecycle, mapProps } from 'recompose'
import { Recommendation } from './Recommendation'

const enhance = compose(
  mapProps(({ recommendations = {}, userRecs = {}, type, ...props }) => {
    const data = type === 'recs' ? recommendations : userRecs
    const recs = Object.keys(data).map(rec => ({
      name: rec,
      value: data[rec]
    }))
    return { data: recs }
  }),
  mapProps(({ data }) => {
    const sorted = [...data].sort((a, b) => b.value - a.value).filter((val, index) => index < 1000)
    return { data: sorted}
  })
)

export const Recommendations = enhance(({ data }) => (
  <div>
    { data.map(d => <Recommendation {...d} />) }
  </div>
))
