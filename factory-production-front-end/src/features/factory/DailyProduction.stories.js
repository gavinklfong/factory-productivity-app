import { Box, Grommet } from "grommet";
import { grommet } from "grommet/themes";
import { DailyProduction } from "./DailyProduction";

export default {
    title: 'DailyProduction',
    component: DailyProduction
}

export const Default = () => (
  <Grommet theme={grommet}>
    <Box align="center" pad="large">
      <DailyProduction />
    </Box>
  </Grommet>
);