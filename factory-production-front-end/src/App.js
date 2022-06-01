import React from 'react';
import { Grommet, Grid, Box, ResponsiveContext } from 'grommet';
import { DailyProduction } from './features/factory/DailyProduction';
import './App.css';

function App() {

  return (
      <Grommet plain>
        <ResponsiveContext.Consumer>
          {(size) => (
            <Box fill>
              <Grid
                fill
                columns={["xsmall", "auto", "xsmall"]}
                rows={["auto"]}
                areas={[["left-margin", "content", "right-margin"]]}>
                  <Box gridArea="left-margin" />
                  <Box gridArea="content">
                    <DailyProduction />
                  </Box>
                  <Box gridArea="right-margin" />
              </Grid>
            </Box>
          )}
      </ResponsiveContext.Consumer>
      </Grommet>
  );
}

export default App;
